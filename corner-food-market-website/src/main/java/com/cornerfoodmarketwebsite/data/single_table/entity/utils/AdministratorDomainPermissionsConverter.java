package com.cornerfoodmarketwebsite.data.single_table.entity.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumMap;
import java.util.HashMap;

@Converter
public class AdministratorDomainPermissionsConverter implements AttributeConverter<EnumMap<AdministratorPermissionDomainEnum, EnumMap<AdministratorPermissionTypeEnum, HashMap<String, Object>>>, String>  {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(EnumMap<AdministratorPermissionDomainEnum, EnumMap<AdministratorPermissionTypeEnum, HashMap<String, Object>>> administratorPermissionDomainEnumEnumMapEnumMap) {
        return objectMapper.writeValueAsString(administratorPermissionDomainEnumEnumMapEnumMap);
    }

    @SneakyThrows
    @Override
    public EnumMap<AdministratorPermissionDomainEnum, EnumMap<AdministratorPermissionTypeEnum, HashMap<String, Object>>> convertToEntityAttribute(String s) {
        TypeReference<EnumMap<AdministratorPermissionDomainEnum, EnumMap<AdministratorPermissionTypeEnum, HashMap<String, Object>>>> typeRef
                = new TypeReference<>() {
        };
        return objectMapper.readValue(s, typeRef);
    }
}
