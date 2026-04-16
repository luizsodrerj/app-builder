package appbuilder.backapp.core.data;

public record FieldValueDTO(
        String id,
        String val,
        String registerId,
        String fieldId,
        String formatType,
        String dataType
    ) {

}
