package appbuilder.backapp.core.usecase;

import appbuilder.backapp.core.entity.App;

public interface CreateAppUseCase {

    void persistAppForm(App app);

    void insertApp(App app);

}
