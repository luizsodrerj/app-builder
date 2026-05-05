package webappbuilder.webappfaces.view.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import appbuilder.core.data.FieldDTO;
import appbuilder.core.data.FormDTO;
import appbuilder.core.entity.Field;
import appbuilder.core.entity.FieldValue;
import appbuilder.core.entity.Form;
import appbuilder.core.entity.FormRegister;
import webappbuilder.webappfaces.data.dto.FieldDTOWrapper;
import webappbuilder.webappfaces.data.dto.FormRegisterDTOWrapper;
import webappbuilder.webappfaces.faces.FacesUtil;
import webappbuilder.webappfaces.service.FormService;

@Component("formDataBean")
@Scope("session")
public class FormDataBean {

    private List<FieldDTOWrapper>fields = new ArrayList<>();
    private List<FormRegisterDTOWrapper>registers = new ArrayList<>();
    private List<String> columns = new ArrayList<>();
    private List<FormDTO>forms = new ArrayList<>();
    private FormDTO form = new FormDTO();

    @Autowired
    private FormService formService;


    public void onClickBtSalvar() {
        FormRegister register = new FormRegister();
        fields.forEach(fieldValue -> {
            String val       = FacesUtil.getParameter("f"+fieldValue.getId());
            FieldValue value = new FieldValue();
            Field field      = new Field();
            field.setId(Long.valueOf(fieldValue.getId()));
            value.setField(field);
            value.setVal(val);
            register.setForm(new Form());
            register.getForm().setId(Long.valueOf(form.getId()));
            register.getFieldValues().add(value);
        });
        formService.createRegister(register);
        getLastRegisters();
    }

    private void getLastRegisters() {
        registers.clear();
        registers.addAll(
            formService.getAllRegisters(
                Long.valueOf(form.getId())
            )
        );
    }

    public void onClickBtPesquisar() {
        getLastRegisters();
    }

    public String onClickBtGetForms() {
        Long appId = Long.valueOf(FacesUtil.getParameter("appId"));
        forms.clear();
        forms.addAll(formService.getForms(appId));        

        return "/form-data/forms.xhtml";
    }

    public String initializeForm() {
        Long formId = Long.valueOf(FacesUtil.getRequest().getParameter("formId"));
        FormDTO dto = formService.findById(formId);

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

    public List<FieldDTOWrapper> getFields() {
        return fields;
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

    public FormDTO getForm() {
        return form;
    }
}








