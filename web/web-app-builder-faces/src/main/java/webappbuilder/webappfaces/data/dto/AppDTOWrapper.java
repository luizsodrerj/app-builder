package webappbuilder.webappfaces.data.dto;

import appbuilder.core.data.AppDTO;

public class AppDTOWrapper extends AppDTO {

    public static final String DESC_DESABILITADA = "Desabilitada";
    public static final String DESC_HABILITADA= "Habilitada";

    public static final String DESABILITADA = "2";
    public static final String HABILITADA = "1";


    public AppDTOWrapper(AppDTO app) {
        setStatus(app.getStatus());
        setAppId(app.getAppId());
        setForms(app.getForms());
        setName(app.getName());
    }

    public AppDTOWrapper(){}

    public boolean isDisabled() {
        String status   = getStatus();
        boolean notNull = status != null && !status.equals("");

        return notNull && status.equals(AppDTOWrapper.DESABILITADA);
    }

    public boolean isEnabled() {
        String status   = getStatus();
        boolean notNull = status != null && !status.equals("");
        boolean isNull  = status == null || status.equals("");
        boolean enabled = notNull && status.equals(AppDTOWrapper.HABILITADA);

        return isNull || enabled;
    }


    public String getDescStatus() {
        String status = getStatus();
        String desStatus  = "";

        if (status != null && !status.equals("")) {
            desStatus = status.equals(AppDTOWrapper.HABILITADA) ?
                        AppDTOWrapper.DESC_HABILITADA :
                        AppDTOWrapper.DESC_DESABILITADA;
        } else {
            desStatus = AppDTOWrapper.DESC_HABILITADA;
        }
        return desStatus;
    }

}
