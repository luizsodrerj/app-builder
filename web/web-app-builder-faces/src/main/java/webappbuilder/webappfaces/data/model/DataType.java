package webappbuilder.webappfaces.data.model;

public class DataType {

    public static final String TEXT = "1";
    public static final String MONETARY = "2";
    public static final String DATE_TIME = "3";

    
    public static boolean isMonetaryDataType(String typeId) {
        return DataType.MONETARY.equals(typeId);
    }


}
