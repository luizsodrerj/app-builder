package appbuilder.backapp.controller;

import appbuilder.backapp.adapter.mapper.FieldMapper;
import appbuilder.backapp.adapter.mapper.FormMapper;
import appbuilder.backapp.adapter.mapper.FormRegisterMapper;
import appbuilder.backapp.core.data.FormDTO;
import appbuilder.backapp.core.data.FormRegisterDTO;
import appbuilder.backapp.core.entity.Form;
import appbuilder.backapp.core.entity.FormRegister;
import appbuilder.backapp.core.usecase.CreateRegisterUseCase;
import appbuilder.backapp.core.usecase.FindFormUseCase;
import appbuilder.backapp.core.usecase.FindRegisterUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/form")
@CrossOrigin(origins = "http://localhost:4200")
public class FormController {
    @Autowired
    private CreateRegisterUseCase createRegisterUseCase;
    @Autowired
    private FindFormUseCase findFormUseCase;
    @Autowired
    private FindRegisterUseCase findRegisterUseCase;



    @PostMapping("new-register")
    public ResponseEntity<FormRegisterDTO> createRegister(@RequestBody FormRegisterDTO dto) {
        FormRegister register = new FormRegisterMapper().toEntity(dto);
        createRegisterUseCase.createRegister(register);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/get-registers-by-values")
    public List<FormRegisterDTO> getByFormRegisterValues(@RequestBody FormRegisterDTO dto) {
        List<FormRegister>result = findRegisterUseCase.findByFormRegisterValues(dto);

        return toDTOList(result);
    }

    @GetMapping("/last-registers/{formId}")
    public List<FormRegisterDTO> getLastRegisters(@PathVariable String formId) {
        List<FormRegister>result = findRegisterUseCase.findAllRegisters(Long.valueOf(formId));

        return toDTOList(result);
    }

    private List<FormRegisterDTO> toDTOList(List<FormRegister>list) {
        List<FormRegisterDTO>data = new ArrayList<>();
        list.forEach(register -> {
            data.add(new FormRegisterMapper().toDTO(register));
        });
        return data;
    }

    @GetMapping("/{id}")
    public FormDTO getForm(
        @PathVariable("id")
        String id
    ) {
        Form form = findFormUseCase.findById(Long.valueOf(id));
        FormDTO dto = new FormMapper().toDto(form);
        form.getFields().forEach(field -> {
            dto.getFields().add(
               new FieldMapper().toDTO(field)
            );
        });
        return dto;
    }

}
