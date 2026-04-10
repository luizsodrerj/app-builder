package appbuilder.backapp.dataprovider.repository;

import appbuilder.backapp.core.entity.FormRegister;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRegisterRepository extends JpaRepository<FormRegister, Long>, FormRegisterSearchRepository {

    List<FormRegister> findByFormIdOrderByIdDesc(Long id, Pageable pageable);


}
