package webappbuilder.webappfaces.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import appbuilder.core.data.FormDTO;
import appbuilder.core.entity.Form;
import appbuilder.core.entity.FormRegister;
import appbuilder.core.mapper.FieldMapper;
import appbuilder.core.mapper.FormMapper;
import webappbuilder.webappfaces.data.dto.FormRegisterDTOWrapper;
import webappbuilder.webappfaces.data.mapper.FieldWrapperMapper;
import webappbuilder.webappfaces.repository.FieldValueWebRepository;
import webappbuilder.webappfaces.repository.FieldWebRepository;
import webappbuilder.webappfaces.repository.FormRegisterWebRepository;
import webappbuilder.webappfaces.repository.FormWebRepository;

@Service
public class FormService {

    @Autowired
    private FormWebRepository formRepository;

    @Autowired
    private FormRegisterWebRepository registerRepository;

    @Autowired
    private FieldValueWebRepository fieldValueRepository;

    @Autowired
    private FieldWebRepository fieldRepository;


    @Transactional
    public List<FormRegisterDTOWrapper> getAllRegisters(Long formId) {
        List<FormRegister>result = registerRepository.findByFormIdOrderByIdDesc(
                                     formId, PageRequest.of(0, 5)
                                   );
        return new FieldWrapperMapper().toDTOList(result);                                   
    }

    @Transactional
    public void createRegister(FormRegister register) {
        register.setForm(formRepository.findById(register.getForm().getId()).get());
        FormRegister savedReg = registerRepository.save(register);

        register.getFieldValues().forEach(fieldValue -> {
            fieldValue.setField(fieldRepository.findById(fieldValue.getField().getId()).get());
            fieldValue.setFormRegister(savedReg);
            fieldValueRepository.save(fieldValue);
        });
    }

    public List<FormDTO> getForms(Long appId) {
        return formRepository.findByAppId(Long.valueOf(appId))
                             .stream()
                             .map(form -> new FormMapper().toDto(form))
                             .collect(Collectors.toList());
    }

    @Transactional 
    public FormDTO findById(Long id) {
        Form form   = formRepository.findById(id).get();
        FormDTO dto = new FormMapper().toDto(form);
        form.getFields().forEach(field -> {
            dto.getFields().add(
                new FieldMapper().toDTO(field)
            );
        });
        return dto;
    }

}
