package appbuilder.backapp.dataprovider.repository;

import appbuilder.backapp.core.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByAppId(Long appId);

}
