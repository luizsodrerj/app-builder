package webappbuilder.webappfaces.view.bean;

import webappbuilder.webappfaces.faces.FacesUtil;
import webappbuilder.webappfaces.view.helper.AppAttributeHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("indexAppBean")
@Scope("request")
public class IndexAppBean {

    private List<AppAttributeHelper>appAttributes = new ArrayList<>();

    public String init()  {
        appAttributes.addAll(
            Arrays.asList(new AppAttributeHelper[] {
                new AppAttributeHelper(
                    "Criar Aplica\u00E7\u00E3o",
                    "Clique no bot\u00E3o abaixo para Criar uma Nova Aplica\u00E7\u00E3o",
                    FacesUtil.getRequest().getContextPath()+"/faces/cad-app/cad-app.xhtml", //
                    "Criar uma Nova Aplica\u00E7\u00E3o",
                    true,
                    "0",
                    "appId",
                    "pi pi-home"
                ),
                new AppAttributeHelper(
                    "Configurar Aplica\u00E7\u00E3o",
                    "Clique no bot\u00E3o abaixo para Configurar uma Aplica\u00E7\u00E3o Existente",
                    FacesUtil.getRequest().getContextPath()+"/faces/cad-app/trigger-all-apps.xhtml",
                    "Configurar uma Aplica\u00E7\u00E3o",
                    false,
                    null,
                    null,
                    "pi pi-check"
                ),
                new AppAttributeHelper(
                    "Iniciar Aplica\u00E7\u00E3o",
                    "Clique no bot\u00E3o abaixo para Iniciar uma Aplica\u00E7\u00E3o",
                    FacesUtil.getRequest().getContextPath()+"/faces/cad-app/init-list-apps.xhtml",
                    "Iniciar uma Aplica\u00E7\u00E3o",
                    false,
                    null,
                    null,
                    "pi pi-sitemap"
                )
            })
        );
        return "/index-app/index-app.xhtml";
    }

    public List<AppAttributeHelper> getAppAttributes() {
        return appAttributes;
    }
}
