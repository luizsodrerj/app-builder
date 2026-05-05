package webappbuilder.webappfaces.data.mapper;

import java.util.ArrayList;
import java.util.List;

import appbuilder.core.entity.FieldAttribute;
import appbuilder.core.entity.FieldValue;
import appbuilder.core.entity.FormRegister;
import framework.util.FormatNumberUtil;
import framework.util.NumberUtil;
import webappbuilder.webappfaces.data.dto.FieldDTOWrapper;
import webappbuilder.webappfaces.data.dto.FormRegisterDTOWrapper;
import webappbuilder.webappfaces.data.model.DataType;

public class FieldWrapperMapper {

    public FormRegisterDTOWrapper toDTO(FormRegister register) {
        List<FieldDTOWrapper>values = new ArrayList<>();

        register.getFieldValues().forEach(fieldValue -> {
            FieldAttribute fieldAttribute = fieldValue.getField().getFieldAttributes().get(0);
            String formatType = fieldAttribute.getFormatType() != null ?
                                fieldAttribute.getFormatType().toString() :
                                "";
            String value = formatFieldValue(fieldAttribute, fieldValue);
            values.add(
                new FieldDTOWrapper(
                    fieldValue.getId().toString(),
                    value,
                    fieldValue.getFormRegister().getId().toString(),
                    fieldValue.getField().getId().toString(),
                    formatType,
                    fieldAttribute.getDataType().toString(),
                    fieldAttribute.getLabel()
                )
            );
        });

        return new FormRegisterDTOWrapper(
            register.getId().toString(),
            values,
            register.getForm().getId().toString()
        );
    }

    private String formatFieldValue(FieldAttribute fieldAttribute,FieldValue fieldValue) {
        boolean isMonetary = DataType.isMonetaryDataType(fieldAttribute.getDataType().toString());
        boolean isNotEmpty = fieldValue.getVal() != null && !"".equals(fieldValue.getVal());
        return  isNotEmpty && isMonetary ?
                FormatNumberUtil.format(
                    NumberUtil.removeFormat(fieldValue.getVal()), 
                    FormatNumberUtil.DUAS_CASAS_DECIMAIS
                ) :
                fieldValue.getVal();
    }



}
