package appbuilder.backapp.util;

import appbuilder.backapp.core.data.AppDTO;
import appbuilder.backapp.core.data.FieldDTO;
import appbuilder.backapp.core.data.FormDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DtoToJsonConverter {

    public static String toJSON(Object dto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String prettyJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);

            System.out.println(prettyJsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DtoToJsonConverter.toJSON(new FieldDTO());
    }
}
