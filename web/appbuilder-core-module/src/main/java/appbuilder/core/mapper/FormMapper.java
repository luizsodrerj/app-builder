package appbuilder.core.mapper;

import appbuilder.core.data.FormDTO;
import appbuilder.core.entity.Form;

public class FormMapper {

    public Form toEntity(FormDTO dto) {
        Form form = new Form();

        form.setNome(dto.getNome());
        form.setLabel(dto.getLabel());

        return form;
    }

    public FormDTO toDto(Form form) {
        FormDTO dto = new FormDTO();
        dto.setId(form.getId().toString());
        dto.setNome(form.getNome());
        dto.setLabel(form.getLabel());

        return dto;
    }

}
