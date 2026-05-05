package webappbuilder.webappfaces.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appbuilder.core.entity.App;

public interface AppWebRepository extends JpaRepository<App, Long> {

    List<App>findByStatusOrStatusIsNull(Integer status);

}
