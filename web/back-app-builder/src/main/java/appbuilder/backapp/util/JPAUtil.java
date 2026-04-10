package appbuilder.backapp.util;

import jakarta.persistence.Query;

import java.util.Map;
import java.util.Set;

public class JPAUtil {

    public static void setQueryParams(Query query, Map<String,Object>params) {
        Set<String>keys = params.keySet();

        keys.forEach(key -> {
            query.setParameter(key, params.get(key));
        });
    }


}
