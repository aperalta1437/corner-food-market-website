package com.cornerfoodmarketwebsite.data.single_table.entity.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TfaTypeConverter implements AttributeConverter<TfaType, String> {
    @Override
    public String convertToDatabaseColumn(TfaType tfaType) {
        if (tfaType == null) {
            return null;
        }
        return tfaType.name();
    }

    @Override
    public TfaType convertToEntityAttribute(String strTfaType) {
        if (strTfaType == null) {
            return null;
        }
        return Stream.of(TfaType.values())
                .filter(currTfaType -> currTfaType.name().equals(strTfaType))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
