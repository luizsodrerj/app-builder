package appbuilder.backapp.controller;

import appbuilder.backapp.adapter.mapper.FieldMapper;
import appbuilder.backapp.adapter.mapper.FormMapper;
import appbuilder.backapp.core.data.FormDTO;
import appbuilder.backapp.core.entity.Form;
import appbuilder.backapp.core.usecase.FindFormUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form")
@CrossOrigin(origins = "http://localhost:4200")
public class FormController {
    @Autowired
    private FindFormUseCase findFormUseCase;

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
