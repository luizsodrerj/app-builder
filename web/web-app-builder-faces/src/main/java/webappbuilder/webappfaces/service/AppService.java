package webappbuilder.webappfaces.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appbuilder.core.mapper.FormMapper;
import webappbuilder.webappfaces.repository.AppWebRepository;
import webappbuilder.webappfaces.repository.FormWebRepository;
import appbuilder.core.data.AppDTO;
import appbuilder.core.data.enums.StatusApp;
import appbuilder.core.entity.App;

@Service
public class AppService {

    @Autowired
    private AppWebRepository appRepository;
    @Autowired
    private FormWebRepository formRepository;


    public List<App> findAllWithAnyStatus() {
        return appRepository.findAll();
    }

    public List<App> findAll() {
        return appRepository.findByStatusOrStatusIsNull(StatusApp.HABILITADA.getStatus());
    }

    public AppDTO findById(Long appId) {
        AppDTO dto = new AppDTO();
        App app = appRepository.findById(appId).get();
        
        dto.setAppId(app.getId().toString());
        dto.setName(app.getNome());
        dto.setForms(
          formRepository.findByAppId(appId).stream()
                         .map(form -> new FormMapper().toDto(form))
                         .collect(Collectors.toList())        
        );
        return dto;
    }

}