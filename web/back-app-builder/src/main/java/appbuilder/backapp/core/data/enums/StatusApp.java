package appbuilder.backapp.core.data.enums;

public enum StatusApp {
    
    HABILITADA(1), DESABILITADA(2);

    private Integer status;

    StatusApp(Integer status) {
        this.status = status;
    }

    public static StatusApp getStatus(Integer statusId) {
        return statusId ==  StatusApp.HABILITADA.getStatus() ?
                            StatusApp.HABILITADA :
                            StatusApp.DESABILITADA;
    } 

    public Integer getStatus() {
        return status;
    }


}
