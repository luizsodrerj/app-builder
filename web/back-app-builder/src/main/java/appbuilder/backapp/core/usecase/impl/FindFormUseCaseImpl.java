package appbuilder.backapp.core.usecase.impl;

import appbuilder.backapp.core.entity.Form;
import appbuilder.backapp.core.usecase.FindFormUseCase;
import appbuilder.backapp.dataprovider.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindFormUseCaseImpl implements FindFormUseCase {
    @Autowired
    private FormRepository formRepository;


    @Override
    public Form findById(Long id) {
        return formRepository.findById(id).get();
    }

    @Override
    public List<Form> findAll(Long appId) {
        return formRepository.findByAppId(appId);
    }
}
