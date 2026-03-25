package appbuilder.backapp.core.usecase;

import appbuilder.backapp.core.entity.Form;

import java.util.List;

public interface FindFormUseCase {

    Form findById(Long id);

    List<Form> findAll(Long idApp);

}

