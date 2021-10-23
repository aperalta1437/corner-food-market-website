package com.cornerfoodmarketwebsite.data.single_table.entity.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TfaTypeConverter implements AttributeConverter<TfaTypeEnum, String> {
    @Override
    public String convertToDatabaseColumn(TfaTypeEnum tfaTypeEnum) {
        if (tfaTypeEnum == null) {
            return null;
        }
        return tfaTypeEnum.name();
    }

    @Override
    public TfaTypeEnum convertToEntityAttribute(String strTfaType) {
        if (strTfaType == null) {
            return null;
        }
        return Stream.of(TfaTypeEnum.values())
                .filter(currTfaTypeEnum -> currTfaTypeEnum.name().equals(strTfaType))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
