package appbuilder.backapp.core.usecase;

import appbuilder.backapp.core.data.FormRegisterDTO;
import appbuilder.backapp.core.entity.FormRegister;

import java.util.List;

public interface FindRegisterUseCase {

    public List<FormRegister> findByFormRegisterValues(FormRegisterDTO register);

    List<FormRegister> findAllRegisters(Long formId);


}
