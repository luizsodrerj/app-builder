package appbuilder.backapp.core.usecase.impl;

import appbuilder.backapp.core.data.enums.StatusApp;
import appbuilder.backapp.core.entity.App;
import appbuilder.backapp.core.usecase.FindAppUseCase;
import appbuilder.backapp.dataprovider.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAppUseCaseImpl implements FindAppUseCase {

    @Autowired
    private AppRepository appRepository;

    @Override
    public List<App> findAll() {
        return appRepository.findByStatusOrStatusIsNull(StatusApp.HABILITADA.getStatus());
    }

    @Override
    public List<App> findAllWithAnyStatus() {
        return appRepository.findAll();
    }

    @Override
    public App findById(Long id) {
        return appRepository.findById(id).get();
    }
}
