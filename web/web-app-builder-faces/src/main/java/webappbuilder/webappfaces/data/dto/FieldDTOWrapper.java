package webappbuilder.webappfaces.data.dto;

import java.io.Serializable;

import appbuilder.core.data.FieldDTO;
import webappbuilder.webappfaces.data.model.DataType;

public class FieldDTOWrapper extends FieldDTO implements Serializable {

    private String registerId;
    private String valueId;
    private String value;

    
    public FieldDTOWrapper(
            String valueId,
            String val,
            String registerId,
            String fieldId,
            String formatType,
            String dataType,
            String label
        ) {
        this.registerId = registerId;
        this.valueId    = valueId;
        this.value      = val;
        setFormatType(formatType);
        setDataType(dataType);
        setLabel(label);
        setId(fieldId);
    }

    public FieldDTOWrapper() {
        super();
    }

    public boolean isTypeOfMonetaryData() {
        String dataType = getDataType();

        return  dataType != null && dataType.equals(DataType.MONETARY);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    
    
}
