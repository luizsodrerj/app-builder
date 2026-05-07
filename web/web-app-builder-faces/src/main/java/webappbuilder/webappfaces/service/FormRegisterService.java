package webappbuilder.webappfaces.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import appbuilder.core.data.FormRegisterDTO;
import appbuilder.core.entity.FieldValue;
import webappbuilder.webappfaces.data.dto.FormRegisterDTOWrapper;
import webappbuilder.webappfaces.data.dto.SumValueDTO;
import webappbuilder.webappfaces.data.mapper.FieldWrapperMapper;
import webappbuilder.webappfaces.repository.FieldValueWebRepository;
import webappbuilder.webappfaces.repository.FormRegisterWebRepository;

@Service
public class FormRegisterService {
    @Autowired
    private FormRegisterWebRepository repository;

    @Autowired
    private FieldValueWebRepository fieldRepository;

    @Transactional
    public void updateRegister(FormRegisterDTOWrapper register) {
        register.getValues().forEach(field -> {
            FieldValue fieldValue = fieldRepository.findById(Long.valueOf(field.getValueId())).get();
            fieldValue.setVal(field.getValue());
            fieldRepository.save(fieldValue);
        });
    }

    @Transactional
    public List<FormRegisterDTOWrapper> findByFormRegisterValues(
            FormRegisterDTO register,
            List<SumValueDTO>sumValues
        ) {
        sumValues.forEach(sumValue -> {
            repository.findByFieldTotalValue(sumValue);
        });
        
        return new FieldWrapperMapper().toDTOList(
                repository.findByFormRegisterValues(register)
            );
    }

    @Transactional
    public List<FormRegisterDTOWrapper> findByFormRegisterValues(FormRegisterDTO register) {
        return new FieldWrapperMapper().toDTOList(
                repository.findByFormRegisterValues(register)
            );
    }


}
