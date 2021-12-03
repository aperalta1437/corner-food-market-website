package com.cornerfoodmarketwebsite.data.single_table.entity.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DiscountTypeNameConverter implements AttributeConverter<DiscountTypeNameEnum, String> {
    @Override
    public String convertToDatabaseColumn(DiscountTypeNameEnum discountTypeNameEnum) {
        if (discountTypeNameEnum == null) {
            return null;
        }
        return discountTypeNameEnum.name();
    }

    @Override
    public DiscountTypeNameEnum convertToEntityAttribute(String strDiscountTypeName) {
        if (strDiscountTypeName == null) {
            return null;
        }
        return Stream.of(DiscountTypeNameEnum.values())
                .filter(currDiscountTypeNameEnum -> currDiscountTypeNameEnum.name().equals(strDiscountTypeName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

