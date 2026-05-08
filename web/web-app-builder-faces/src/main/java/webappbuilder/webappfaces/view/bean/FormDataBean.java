package webappbuilder.webappfaces.view.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import appbuilder.core.data.FieldDTO;
import appbuilder.core.data.FieldValueDTO;
import appbuilder.core.data.FormDTO;
import appbuilder.core.entity.Field;
import appbuilder.core.entity.FieldValue;
import appbuilder.core.entity.Form;
import appbuilder.core.entity.FormRegister;
import jakarta.annotation.PostConstruct;
import webappbuilder.webappfaces.data.dto.FieldDTOWrapper;
import webappbuilder.webappfaces.data.dto.FormRegisterDTOWrapper;
import webappbuilder.webappfaces.data.dto.SumValueDTO;
import webappbuilder.webappfaces.faces.FacesUtil;
import webappbuilder.webappfaces.service.FormRegisterService;
import webappbuilder.webappfaces.service.FormService;
import webappbuilder.webappfaces.view.helper.FormDataBeanViewHelper;

@Component("formDataBean")
@Scope("session")
public class FormDataBean {

    private static final String OP_UPDATE = "OP_UPDATE";
    private static final String OP_CREATE = "OP_CREATE";

    private List<FieldDTOWrapper>fields = new ArrayList<>();
    private List<FormRegisterDTOWrapper>registers = new ArrayList<>();
    private List<String> columns = new ArrayList<>();
    private List<FormDTO>forms = new ArrayList<>();
    private FormRegisterDTOWrapper selectedRegister;
    private FormDTO form = new FormDTO();
    private String operation = FormDataBean.OP_CREATE;
    private FormDataBeanViewHelper dataBeanHelper; 
    private List<SumValueDTO>sumValuesList = new ArrayList<>();

    @Autowired
    private FormRegisterService formRegisterService; 
    @Autowired
    private FormService formService;



    @PostConstruct
    public void postConstruct() {
        dataBeanHelper = new FormDataBeanViewHelper(this);
    }

    public void onClickBtSalvar() {
        if (operation.equals(OP_CREATE)) {
            createRegister();
        } else {
            updateRegister();
        }
    }

    public void createRegister() {
        FormRegister register = new FormRegister();
        register.setForm(new Form());
        register.getForm().setId(Long.valueOf(form.getId()));

        fields.forEach(fieldValue -> {
            String val       = FacesUtil.getParameter("f"+fieldValue.getId());
            FieldValue value = new FieldValue();
            Field field      = new Field();
            field.setId(Long.valueOf(fieldValue.getId()));
            value.setField(field);
            value.setVal(val);
            register.getFieldValues().add(value);
        });
        formService.createRegister(register);
        PrimeFaces.current().executeScript("PF('dlgSuccess').show();");
        getLastRegisters();
    }

    private void updateRegister() { 
        populateValues();
        formRegisterService.updateRegister(selectedRegister);
        operation = FormDataBean.OP_CREATE;
        PrimeFaces.current().executeScript("PF('dlgSuccess').show();");
        getLastRegisters();
        resetFields();
    }

    private void populateValues() {
        selectedRegister.getValues().forEach(fieldValue -> {
            String val = FacesUtil.getParameter("f"+fieldValue.getId());
            fieldValue.setValue(val);
        });
    }

    public void onRowSelect(SelectEvent<FormRegisterDTOWrapper> event) {
        FormRegisterDTOWrapper register = event.getObject();
        this.selectedRegister = register;

        fields.forEach(field -> {
            register.getValues().forEach(fieldValue -> {
                if (fieldValue.getId().equals(field.getId())) {
                    field.setValue(fieldValue.getValue());
                }
            });
        });
        operation = FormDataBean.OP_UPDATE;
        FacesUtil.handleNavigation("/form-data/form-data.xhtml?faces-redirect=true");
    } 

    public void onClickBtExportData() {
        dataBeanHelper.exportData();
    }

    public void onClickBtPesquisar() {
        List<FieldValueDTO>values = new ArrayList<>();
        populateValues(values);
        dataBeanHelper.populateSumValues(values);

        if (!values.isEmpty()) {
            populateTable(dataBeanHelper.findByFormRegisterValues(formRegisterService,values));    
        } else {
            getLastRegisters();
        }
        selectedRegister = null;
    }

    public void populateValues(List<FieldValueDTO> values) {
        fields.forEach(field -> { 
            String val = FacesUtil.getParameter("f"+field.getId());
            if (StringUtils.isNotBlank(val)) {
                values.add(
                    new FieldValueDTO(
                        null,
                        val,
                        null,
                        null,
                        null,
                        null
                    )                    
                );
            }
        });
    }

    private void getLastRegisters() {
        List<FormRegisterDTOWrapper>regData = formService.getAllRegisters(Long.valueOf(form.getId()));
        populateTable(regData);
    }

    private void populateTable(List<FormRegisterDTOWrapper>regData) {
        registers.clear();
        regData.forEach(register -> {
            List<FieldDTOWrapper>values = new ArrayList<>(register.getValues());
            register.getValues().clear();
            populateValues(values, register);
            registers.add(register);
        });
    }

    private void populateValues(List<FieldDTOWrapper>values, FormRegisterDTOWrapper register) {
        columns.forEach(column -> {
            values.forEach(fieldValue -> {
                if (fieldValue.getLabel().equals(column)) {
                    register.getValues().add(fieldValue);
                }
            });
        });
    }

    public String onClickBtGetForms() {
        Long appId = Long.valueOf(FacesUtil.getParameter("appId"));
        forms.clear();
        forms.addAll(formService.getForms(appId));        

        return "/form-data/forms.xhtml";
    }

    public void onClickBtLimpar() {
        resetFields();
    }

    public String initializeForm() {
        Long formId = Long.valueOf(FacesUtil.getRequest().getParameter("formId"));
        FormDTO dto = formService.findById(formId);

        this.sumValuesList.clear();
        this.registers.clear();
        this.columns.clear();
        this.fields.clear();

        this.form.setLabel(dto.getLabel());
        this.form.setId(dto.getId());
        populateFields(dto);

        return "/form-data/form-data.xhtml";
    }

    private void populateFields(FormDTO formDTO) {
        formDTO.getFields().forEach(fieldData -> {
            FieldDTOWrapper fieldWrapper = new FieldDTOWrapper();
            copy(fieldWrapper, fieldData);
            fields.add(fieldWrapper);
        });
        fields.forEach(field -> {
            columns.add(field.getLabel());
        });
    }

    private void copy(FieldDTOWrapper fieldWrapper, FieldDTO fieldData) {
        try {
            BeanUtils.copyProperties(fieldWrapper, fieldData);
            fieldWrapper.setValue("");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void resetFields() {
        selectedRegister = null;
        operation = FormDataBean.OP_CREATE;

        fields.forEach(field -> {
            field.setValue("");
        });
    }

    public List<FieldDTOWrapper> getFields() {
        return fields;
    }

    public void setSelectedRegister(FormRegisterDTOWrapper selectedRegister) {
        this.selectedRegister = selectedRegister;
    }

    public FormRegisterDTOWrapper getSelectedRegister() {
        return selectedRegister;
    }

    public List<FormRegisterDTOWrapper> getRegisters() {
        return registers;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<FormDTO> getForms() {
        return forms;
    }

    public FormRegisterService getFormRegisterService() {
        return formRegisterService;
    }

    public List<SumValueDTO> getSumValuesList() {
        return sumValuesList;
    }

    public FormDTO getForm() {
        return form;
    }
}








