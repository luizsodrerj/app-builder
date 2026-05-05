package appbuilder.core.data;

import java.util.List;

public record FormRegisterDTO(
        String id,
        List<FieldValueDTO> values,
        String formId
    ) {
}
