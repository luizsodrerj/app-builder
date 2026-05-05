package webappbuilder.webappfaces.view.bean;

import appbuilder.core.mapper.AppMapper;
import appbuilder.core.data.AppDTO;
import appbuilder.core.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import webappbuilder.webappfaces.data.dto.AppDTOWrapper;
import webappbuilder.webappfaces.faces.FacesUtil;
import webappbuilder.webappfaces.service.AppService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("cadAppBean")
@Scope("request")
public class CadAppBean {

    @Autowired
    private AppService appService;

    private List<AppDTO> apps = new ArrayList<>();
    private AppDTO app = new AppDTO();

    
    public String onClickBtConfigApp() {
        String appId = FacesUtil.getRequest().getParameter("appId");

        app = appService.findById(Long.valueOf(appId));

        return "/cad-app/cad-app.xhtml";
    }

    public void onClickBtEnableApp () {
        String appId = FacesUtil.getRequest().getParameter("appId");
    }

    public void onClickBtDisableApp() {
        String appId = FacesUtil.getRequest().getParameter("appId");

    }

    public void onClickBtSave() {

    }

    public String getAllApps() {
        apps = mapToDTOList(appService.findAll());
        return "/cad-app/list-apps.xhtml";
    }

    public String getAllWithAnyStatus() {
        apps = mapToDTOList(appService.findAllWithAnyStatus());
        return "/cad-app/all-apps.xhtml";
    }

    private List<AppDTO> mapToDTOList(List<App>apps) {
        return apps.stream()
                   .map(app -> new AppDTOWrapper(new AppMapper().toDto(app)))
                   .collect(Collectors.toList());
    }

    public List<AppDTO> getApps() {
        return apps;
    }

    public AppDTO getApp() {
        return app;
    }
}
