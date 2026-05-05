package webappbuilder.webappfaces.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FormRegisterDTOWrapper implements Serializable {

    private String id;
    private List<FieldDTOWrapper> values = new ArrayList<>();
    private String formId;


    public FormRegisterDTOWrapper(
        String id,
        List<FieldDTOWrapper> values,
        String formId
      ) {
        this.id = id;
        this.values = values;
        this.formId = formId;
    }  

    public String getFormId() {
        return formId;
    }

    public List<FieldDTOWrapper> getValues() {
        return values;
    }

    public String getId() {
        return id;
    }

}
