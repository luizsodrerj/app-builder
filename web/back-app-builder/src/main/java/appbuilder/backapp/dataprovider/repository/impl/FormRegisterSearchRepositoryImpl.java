package appbuilder.backapp.dataprovider.repository.impl;

import appbuilder.backapp.core.data.FieldValueDTO;
import appbuilder.backapp.core.data.FormRegisterDTO;
import appbuilder.backapp.core.entity.FormRegister;
import appbuilder.backapp.dataprovider.repository.FormRegisterSearchRepository;
import appbuilder.backapp.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class FormRegisterSearchRepositoryImpl implements FormRegisterSearchRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<FormRegister> findByFormRegisterValues(FormRegisterDTO register) {
        List<FieldValueDTO>values = register.values();
        Map<String,Object>params = new HashMap<>();
        params.put("formId", register.formId());

        String sql =
            "SELECT r " +
            "FROM   FormRegister r " +
            "WHERE  r.form.id = :formId " +
            "AND    r.id IN ( " +
            "  SELECT   f.formRegister.id " +
            "  FROM     FieldValue f " +
            "  WHERE    f.formRegister.form.id = :formId " +
            "  AND (    ";
        final StringBuilder mainSql = new StringBuilder(sql);
        setValues(values, mainSql, params);
        mainSql.append(
            "  ) " +
            ")"
        );
        Query query = entityManager.createQuery(mainSql.toString());
        JPAUtil.setQueryParams(query, params);

        return query.getResultList();
    }

    private void setValues(
        List<FieldValueDTO> values,
        StringBuilder mainSql,
        Map<String,Object>params
    ) {
        if (values.size() > 1) {
            FieldValueDTO firstValue = values.get(0);
            mainSql.append("upper(f.val) LIKE :val ");
            params.put("val", "%"+firstValue.val().toUpperCase()+"%");
            IntStream.range(1, values.size()).forEach(index -> {
                mainSql.append("OR upper(f.val) LIKE :val"+index+" ");
                params.put("val"+index, "%"+values.get(index).val().toUpperCase()+"%");
            });
        } else {
            mainSql.append("upper(f.val) LIKE :val ");
            params.put("val", "%"+ values.get(0).val().toUpperCase()+"%");
        }
    }

}
