package webappbuilder.webappfaces.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import appbuilder.core.entity.FormRegister;

public interface FormRegisterWebRepository extends JpaRepository<FormRegister, Long> {

    List<FormRegister> findByFormIdOrderByIdDesc(Long id, Pageable pageable);

}
