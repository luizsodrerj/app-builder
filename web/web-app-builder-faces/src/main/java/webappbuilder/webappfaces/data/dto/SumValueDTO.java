package webappbuilder.webappfaces.data.dto;

import java.math.BigDecimal;
import java.util.List;

import appbuilder.core.data.FieldValueDTO;
import framework.util.FormatNumberUtil;

public class SumValueDTO {

    private List<FieldValueDTO>values;
    private String fieldId;
    private String formId;
    private BigDecimal totalValue;
    private String label;

    public SumValueDTO(
        List<FieldValueDTO>values,
        String fieldId,
        String formId,
        String label
    ){
        this.values  = values;
        this.fieldId = fieldId;
        this.formId  = formId; 
        this.label = label;
    }

    public List<FieldValueDTO> getValues() {
        return values;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getFormId() {
        return formId;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
    public String getTotalValue() {
        return  totalValue != null ?
                FormatNumberUtil.format(
                    totalValue, 
                    FormatNumberUtil.DUAS_CASAS_DECIMAIS
                ) : 
                "";
    }

    public String getLabel() {
        return label;
    }

    


}
