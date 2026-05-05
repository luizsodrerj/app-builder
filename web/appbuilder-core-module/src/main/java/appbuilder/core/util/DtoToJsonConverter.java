package appbuilder.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import appbuilder.core.data.FieldDTO;

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
