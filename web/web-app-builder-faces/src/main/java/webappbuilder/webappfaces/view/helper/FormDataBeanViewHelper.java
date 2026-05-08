package webappbuilder.webappfaces.view.helper;

import java.util.List;

import org.primefaces.PrimeFaces;

import appbuilder.core.data.FieldValueDTO;
import appbuilder.core.data.FormRegisterDTO;
import webappbuilder.webappfaces.data.dto.FieldDTOWrapper;
import webappbuilder.webappfaces.data.dto.FormRegisterDTOWrapper;
import webappbuilder.webappfaces.data.dto.SumValueDTO;
import webappbuilder.webappfaces.faces.FacesUtil;
import webappbuilder.webappfaces.service.FormRegisterService;
import webappbuilder.webappfaces.view.bean.FormDataBean;

public class FormDataBeanViewHelper {

    private FormRegisterService formRegisterService; 
    private FormDataBean dataBean;


    public FormDataBeanViewHelper(FormDataBean dataBean) {
        this.formRegisterService = dataBean.getFormRegisterService();
        this.dataBean = dataBean;
    }
 
    public void exportData() {
        PrimeFaces.current().executeScript("PF('dlgExportData').show();");
    }

    public List<FormRegisterDTOWrapper>findByFormRegisterValues(
            FormRegisterService service,
            List<FieldValueDTO>values
        ) {
        FormRegisterDTO register = new FormRegisterDTO(null,values,dataBean.getForm().getId());
            
        return  !dataBean.getSumValuesList().isEmpty() ?
                service.findByFormRegisterValues(
                    register,
                    dataBean.getSumValuesList()
                ) :
                service.findByFormRegisterValues(register);
    }

    public void populateSumValues(List<FieldValueDTO>values) {
        List<SumValueDTO>sumValuesList = dataBean.getSumValuesList();
        List<FieldDTOWrapper>fields = dataBean.getFields();
        sumValuesList.clear();
 
        if (!values.isEmpty()) {
            fields.forEach(field -> { 
                String totValue    = FacesUtil.getParameter("total-field-"+field.getId());
                boolean isTotValue = totValue != null && Boolean.valueOf(totValue);

                if (isTotValue) {
                    sumValuesList.add(
                        new SumValueDTO(
                            values,
                            field.getId(),
                            dataBean.getForm().getId(),
                            field.getLabel()
                        )
                    );
                }
            });
        }
    }

}
