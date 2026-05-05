package webappbuilder.webappfaces.faces;

import jakarta.servlet.http.HttpServletRequest; 

import jakarta.faces.context.FacesContext;

public class FacesUtil {


    public static String getParameter(String paramName) {
        return getRequest().getParameter(paramName);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                                               .getExternalContext()
                                               .getRequest();
    }

}
