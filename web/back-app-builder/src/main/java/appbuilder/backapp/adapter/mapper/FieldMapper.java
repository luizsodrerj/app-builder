package appbuilder.backapp.adapter.mapper;

import appbuilder.backapp.core.data.FieldDTO;
import appbuilder.backapp.core.entity.Field;
import appbuilder.backapp.core.entity.FieldAttribute;
import org.apache.commons.lang3.StringUtils;

public class FieldMapper {

    public void copyToDto(Field field, FieldDTO dto) {
        FieldAttribute fieldAttr = field.getFieldAttributes().get(0);
        toDto(field, dto, fieldAttr);
    }

    public void copyToEntity(Field field, FieldDTO dto) {
        FieldAttribute fieldAttr = field.getFieldAttributes().get(0);
        toEntity(dto, field, fieldAttr);
    }

    public FieldDTO toDTO(Field field) {
        FieldDTO dto = new FieldDTO();
        FieldAttribute fieldAttr = field.getFieldAttributes().get(0);

        toDto(field, dto, fieldAttr);

        return dto;
    }

    public Field toEntity(FieldDTO dto) {
        Field field = new Field();
        FieldAttribute fieldAttr = new FieldAttribute();
        field.getFieldAttributes().add(fieldAttr);

        toEntity(dto, field, fieldAttr);

        return field;
    }

    private void toEntity(FieldDTO dto, Field field, FieldAttribute fieldAttr) {
        field.setNome(dto.getName());
        fieldAttr.setDataType(Integer.valueOf(dto.getDataType()));
        fieldAttr.setFieldType(Integer.valueOf(dto.getFieldType()));
        fieldAttr.setFormatType(
            StringUtils.isNotBlank(dto.getFormatType()) ?
            Integer.valueOf(dto.getFormatType()):
            null
        );
        fieldAttr.setLabel(dto.getLabel());
    }

    private void toDto(Field field, FieldDTO dto, FieldAttribute fieldAttr) {
        dto.setId(field.getId().toString());
        dto.setName(field.getNome());
        dto.setDataType(fieldAttr.getDataType().toString());
        dto.setFieldType(fieldAttr.getFieldType().toString());
        dto.setFormatType(fieldAttr.getFormatType() != null ? fieldAttr.getFormatType().toString(): null);
        dto.setLabel(fieldAttr.getLabel());
    }

}
