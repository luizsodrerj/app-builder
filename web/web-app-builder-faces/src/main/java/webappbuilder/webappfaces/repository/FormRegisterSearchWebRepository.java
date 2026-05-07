package webappbuilder.webappfaces.repository;

import java.util.List;

import appbuilder.core.data.FormRegisterDTO;
import appbuilder.core.entity.FormRegister;
import webappbuilder.webappfaces.data.dto.SumValueDTO;

public interface FormRegisterSearchWebRepository {

    List<FormRegister> findByFormRegisterValues(FormRegisterDTO register);

    SumValueDTO findByFieldTotalValue(SumValueDTO sumValue);
}
