package com.cornerfoodmarketwebsite.data.single_table.entity.utils;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Converter
public class AdministratorPermissionListConverter implements AttributeConverter<HashSet<AdministratorPermissionEnum>, String> {
    @Override
    public String convertToDatabaseColumn(HashSet<AdministratorPermissionEnum> administratorPermissionEnumHashSet) {
        if (administratorPermissionEnumHashSet == null) {
            return null;
        }

        // Build the postgres ADMINISTRATOR_PERMISSION ARRAY value.
        return String.format("{%s}", administratorPermissionEnumHashSet.stream().map(AdministratorPermissionEnum::toString).collect(Collectors.joining(",")));
    }

    @Override
    public HashSet<AdministratorPermissionEnum> convertToEntityAttribute(String pgAdministratorPermissionsStr) {
        if (pgAdministratorPermissionsStr == null) {
            return null;
        }

        pgAdministratorPermissionsStr = StringUtils.stripStart(pgAdministratorPermissionsStr, "{");
        pgAdministratorPermissionsStr = StringUtils.stripEnd(pgAdministratorPermissionsStr, "}");

        return Arrays.stream(pgAdministratorPermissionsStr.split(",")).map(AdministratorPermissionEnum::valueOf).collect(Collectors.toCollection(HashSet::new));
    }
}
