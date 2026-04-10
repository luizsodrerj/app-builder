package appbuilder.backapp.core.usecase.impl;

import appbuilder.backapp.core.data.FormRegisterDTO;
import appbuilder.backapp.core.entity.FormRegister;
import appbuilder.backapp.core.usecase.FindRegisterUseCase;
import appbuilder.backapp.dataprovider.repository.FormRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindRegisterUseCaseImpl implements FindRegisterUseCase {
    @Autowired
    private FormRegisterRepository repository;

    public List<FormRegister> findByFormRegisterValues(FormRegisterDTO register) {
        return repository.findByFormRegisterValues(register);
    }

    @Override
    public List<FormRegister> findAllRegisters(Long formId) {
        return repository.findByFormIdOrderByIdDesc(
            formId, PageRequest.of(0, 5)
        );
    }
}





