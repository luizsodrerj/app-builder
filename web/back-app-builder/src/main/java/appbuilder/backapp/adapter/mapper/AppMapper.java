package appbuilder.backapp.adapter.mapper;

import appbuilder.backapp.core.data.AppDTO;
import appbuilder.backapp.core.entity.App;
import appbuilder.backapp.core.entity.Form;
import org.apache.commons.lang3.StringUtils;

public class AppMapper {


    public App deepCopy(AppDTO dto) {
        App app = new App();
        app.setNome(dto.getName());

        if (!dto.getForms().isEmpty()) {
            dto.getForms().forEach(formDTO -> {
                Form form = new FormMapper().toEntity(formDTO);
                formDTO.getFields().forEach(fieldDTO -> {
                    form.getFields().add(
                        new FieldMapper().toEntity(fieldDTO)
                    );
                });
                app.getForms().add(form);
            });
        }
        return app;
    }


    public App toEntity(AppDTO dto) {
        App app = new App();

        app.setNome(dto.getName());
        app.setStatus(
            StringUtils.isNotBlank(dto.getStatus()) ?
            Integer.valueOf(dto.getStatus()) :
            null
        );
        return app;
    }

    public AppDTO toDto(App app) {
        AppDTO dto = new AppDTO();

        dto.setAppId(app.getId().toString());
        dto.setName(app.getNome());
        dto.setStatus(
            app.getStatus() != null ?
            app.getStatus().toString() :
            null
        );
        return dto;
    }

}
