package webappbuilder.webappfaces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appbuilder.core.entity.Form;

public interface FormWebRepository extends JpaRepository<Form, Long> {
    List<Form> findByAppId(Long appId);

}
