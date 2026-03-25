package appbuilder.backapp.core.usecase.impl;

import appbuilder.backapp.core.entity.App;
import appbuilder.backapp.core.entity.Field;
import appbuilder.backapp.core.entity.FieldAttribute;
import appbuilder.backapp.core.entity.Form;
import appbuilder.backapp.core.usecase.CreateAppUseCase;
import appbuilder.backapp.dataprovider.repository.AppRepository;
import appbuilder.backapp.dataprovider.repository.FieldAttributeRepository;
import appbuilder.backapp.dataprovider.repository.FieldRepository;
import appbuilder.backapp.dataprovider.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateAppUseCaseImpl implements CreateAppUseCase {

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    FieldRepository fieldRepository;
    @Autowired
    FieldAttributeRepository fieldAttrRepository;


    @Override
    public void insertApp(App app) {
        List<Form>forms = app.getForms();
        appRepository.save(app);

        if (!forms.isEmpty()) {
            forms.forEach(form -> {
                form.setApp(app);
                formRepository.save(form);

                List<Field>fields = form.getFields();
                fields.forEach(field -> {
                    field.setForm(form);
                    FieldAttribute fieldAttr = field.getFieldAttributes().get(0);
                    fieldRepository.save(field);
                    fieldAttr.setField(field);
                    fieldAttrRepository.save(fieldAttr);
                });
            });
        }
    }
}









