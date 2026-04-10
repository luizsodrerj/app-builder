package appbuilder.backapp.dataprovider.repository;

import appbuilder.backapp.core.data.FormRegisterDTO;
import appbuilder.backapp.core.entity.FormRegister;

import java.util.List;

public interface FormRegisterSearchRepository {

    List<FormRegister> findByFormRegisterValues(FormRegisterDTO register);

}

