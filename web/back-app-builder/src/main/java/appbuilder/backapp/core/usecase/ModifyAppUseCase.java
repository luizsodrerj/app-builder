package appbuilder.backapp.core.usecase;

import appbuilder.backapp.core.data.AppDTO;

public interface ModifyAppUseCase {

    void changeStatus(AppDTO app);

    void update(AppDTO app);
}