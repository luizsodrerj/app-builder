package webappbuilder.webappfaces.faces;

import jakarta.servlet.http.HttpServletRequest; 

import jakarta.faces.context.FacesContext;

public class FacesUtil {

	public static void handleNavigation(String page) {
		FacesContext.getCurrentInstance().getApplication()
					.getNavigationHandler().handleNavigation(
						FacesContext.getCurrentInstance(), 
						null, 
						page
					);
    }
    
    public static String getParameter(String paramName) {
        return getRequest().getParameter(paramName);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                                               .getExternalContext()
                                               .getRequest();
    }

}
