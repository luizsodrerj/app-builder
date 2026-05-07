package webappbuilder.webappfaces.view.helper;

import java.util.List;

import appbuilder.core.data.FieldValueDTO;
import appbuilder.core.data.FormRegisterDTO;
import webappbuilder.webappfaces.data.dto.FieldDTOWrapper;
import webappbuilder.webappfaces.data.dto.FormRegisterDTOWrapper;
import webappbuilder.webappfaces.data.dto.SumValueDTO;
import webappbuilder.webappfaces.faces.FacesUtil;
import webappbuilder.webappfaces.service.FormRegisterService;
import webappbuilder.webappfaces.view.bean.FormDataBean;

public class FormDataBeanViewHelper {

    public List<FormRegisterDTOWrapper>findByFormRegisterValues(
            FormRegisterService service,
            List<FieldValueDTO>values,
            FormDataBean dataBean
        ) {
        return  !dataBean.getSumValuesList().isEmpty() ?
                service.findByFormRegisterValues(
                    new FormRegisterDTO(
                        null,
                        values,
                        dataBean.getForm().getId()
                    ),
                    dataBean.getSumValuesList()
                ) :
                service.findByFormRegisterValues(
                    new FormRegisterDTO(
                        null,
                        values,
                        dataBean.getForm().getId()
                    )
                );
    }

    public void populateSumValues(FormDataBean dataBean,List<FieldValueDTO>values) {
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
