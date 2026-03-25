package appbuilder.backapp.dataprovider.repository;

import appbuilder.backapp.core.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, Long> {

}
