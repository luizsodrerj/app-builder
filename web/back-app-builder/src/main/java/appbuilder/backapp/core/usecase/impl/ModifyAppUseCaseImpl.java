package appbuilder.backapp.core.usecase.impl; 

import appbuilder.backapp.dataprovider.repository.AppRepository;
import appbuilder.backapp.core.usecase.ModifyAppUseCase;
import appbuilder.backapp.core.data.AppDTO;
import appbuilder.backapp.core.data.enums.StatusApp;
import appbuilder.backapp.core.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ModifyAppUseCaseImpl implements ModifyAppUseCase {

    @Autowired
    private AppRepository appRepository;


    @Override
    public void update(AppDTO dto) {
        App app = appRepository.findById(Long.valueOf(dto.getAppId())).get();
        app.setNome(dto.getName());
        appRepository.save(app);
    }

    @Override
    public void changeStatus(AppDTO dto) {
        Integer status = Integer.valueOf(dto.getStatus());
        boolean notHabilitada = !StatusApp.HABILITADA.getStatus().equals(status);
        boolean notDesab = !StatusApp.DESABILITADA.getStatus().equals(status);   

        if (notHabilitada && notDesab) {
            throw new IllegalArgumentException("status da app invalido!");
        }
        App app = appRepository.findById(Long.valueOf(dto.getAppId())).get();
        app.setStatus(status);
        appRepository.save(app);
    }
}