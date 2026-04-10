package appbuilder.backapp.core.usecase.impl;

import appbuilder.backapp.adapter.mapper.FormRegisterMapper;
import appbuilder.backapp.core.data.FormRegisterDTO;
import appbuilder.backapp.core.entity.Form;
import appbuilder.backapp.core.entity.FormRegister;
import appbuilder.backapp.core.usecase.CreateRegisterUseCase;
import appbuilder.backapp.dataprovider.repository.FieldRepository;
import appbuilder.backapp.dataprovider.repository.FieldValueRepository;
import appbuilder.backapp.dataprovider.repository.FormRegisterRepository;
import appbuilder.backapp.dataprovider.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRegisterUseCaseImpl implements CreateRegisterUseCase {
    @Autowired
    private FormRegisterRepository registerRepository;
    @Autowired
    private FieldValueRepository fieldValueRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private FieldRepository fieldRepository;


    @Override
    public void createRegister(FormRegister register) {
        register.setForm(formRepository.findById(register.getForm().getId()).get());
        FormRegister savedReg = registerRepository.save(register);

        register.getFieldValues().forEach(fieldValue -> {
            fieldValue.setField(fieldRepository.findById(fieldValue.getField().getId()).get());
            fieldValue.setFormRegister(savedReg);
            fieldValueRepository.save(fieldValue);
        });
    }
}
