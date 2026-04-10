package appbuilder.backapp.core.data;

import java.util.List;

public record FormRegisterDTO(
        String id,
        List<FieldValueDTO> values,
        String formId
    ) {
}
