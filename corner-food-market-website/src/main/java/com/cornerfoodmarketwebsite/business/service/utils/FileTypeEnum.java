package com.cornerfoodmarketwebsite.business.service.utils;

// NOTE: IDs MUST match their correspond record in the FILE_TYPE table within the database.
public enum FileTypeEnum {
    IMAGE((short) 1);

    private final short fileTypeId;

    FileTypeEnum(short fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public short getFileTypeId() {
        return this.fileTypeId;
    }
}
