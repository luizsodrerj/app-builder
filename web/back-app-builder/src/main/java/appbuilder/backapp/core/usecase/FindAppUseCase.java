package appbuilder.backapp.core.usecase;

import appbuilder.backapp.core.entity.App;

import java.util.List;

public interface FindAppUseCase {
    
    List<App> findAll();

    App findById(Long id);
}
