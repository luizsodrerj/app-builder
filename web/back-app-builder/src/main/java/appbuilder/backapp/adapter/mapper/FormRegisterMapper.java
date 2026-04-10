package appbuilder.backapp.adapter.mapper;

import appbuilder.backapp.core.data.FieldValueDTO;
import appbuilder.backapp.core.data.FormRegisterDTO;
import appbuilder.backapp.core.entity.Field;
import appbuilder.backapp.core.entity.FieldValue;
import appbuilder.backapp.core.entity.Form;
import appbuilder.backapp.core.entity.FormRegister;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FormRegisterMapper {


    public FormRegister toEntity(FormRegisterDTO dto) {
        List<FieldValue>values = new ArrayList<>();
        FormRegister register = new FormRegister();
        Form form = new Form();
        form.setId(Long.valueOf(dto.formId()));
        register.setForm(form);

        register.setId(StringUtils.isNotBlank(dto.id()) ? Long.valueOf(dto.id()) : null);
        register.setFieldValues(values);
        dto.values().forEach(valueDTO -> {
            FieldValue value = new FieldValue();
            Field field = new Field();
            field.setId(Long.valueOf(valueDTO.fieldId()));
            value.setField(field);
            value.setFormRegister(register);
            value.setVal(valueDTO.val());
            values.add(value);
        });
        return register;
    }

    public FormRegisterDTO toDTO(FormRegister register) {
        List<FieldValueDTO>values = new ArrayList<>();

        register.getFieldValues().forEach(fieldValue -> {
            values.add(
                new FieldValueDTO(
                    fieldValue.getId().toString(),
                    fieldValue.getVal(),
                    fieldValue.getFormRegister().getId().toString(),
                    fieldValue.getField().getId().toString()
                )
            );
        });

        return new FormRegisterDTO(
            register.getId().toString(),
            values,
            register.getForm().getId().toString()
        );
    }

}




