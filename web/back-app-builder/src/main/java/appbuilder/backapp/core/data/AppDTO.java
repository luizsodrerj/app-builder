package appbuilder.backapp.core.data;

import java.util.ArrayList;
import java.util.List;

public class AppDTO {
    private String appId;
    private String name;
    private List<FormDTO>forms = new ArrayList<>();


    public List<FormDTO> getForms() {
        return forms;
    }

    public void setForms(List<FormDTO> forms) {
        this.forms = forms;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
