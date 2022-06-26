package com.cornerfoodmarketwebsite.data.single_table.entity.utils;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.stream.Collectors;

@Converter
public class AdministratorPermissionListConverter implements AttributeConverter<EnumSet<AdministratorPermissionEnum>, String> {
    @Override
    public String convertToDatabaseColumn(EnumSet<AdministratorPermissionEnum> administratorPermissionEnumSet) {
        if (administratorPermissionEnumSet == null) {
            return null;
        }

        // Build the postgres ADMINISTRATOR_PERMISSION ARRAY value.
        return String.format("{%s}", administratorPermissionEnumSet.stream().map(AdministratorPermissionEnum::toString).collect(Collectors.joining(",")));
    }

    @Override
    public EnumSet<AdministratorPermissionEnum> convertToEntityAttribute(String pgAdministratorPermissionsStr) {
        if (pgAdministratorPermissionsStr == null) {
            return null;
        }

        pgAdministratorPermissionsStr = StringUtils.stripStart(pgAdministratorPermissionsStr, "{");
        pgAdministratorPermissionsStr = StringUtils.stripEnd(pgAdministratorPermissionsStr, "}");

        return EnumSet.copyOf(Arrays.stream(pgAdministratorPermissionsStr.split(",")).map(AdministratorPermissionEnum::valueOf).collect(Collectors.toList()));

//        return Arrays.stream(pgAdministratorPermissionsStr.split(",")).map(AdministratorPermissionEnum::valueOf).collect(Collectors.toCollection(EnumSet.));
    }
}
