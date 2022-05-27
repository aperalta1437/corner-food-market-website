package com.cornerfoodmarketwebsite.business.service.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class TemporalEncryptedPasswordStore {
    @Getter
    @RequiredArgsConstructor
    private static class EncryptedPasswordAndCreationDateTime {
        private final String encryptedPassword;
        private final LocalDateTime creationDateTime;
    }

    private final Map<Integer, Map<Short, EncryptedPasswordAndCreationDateTime>> store = new HashMap<>();

    public String getEncryptedPassword(int originNumber, Short userId) {
        return this.store.get(originNumber).get(userId).encryptedPassword;
    }

    public void putEncryptedPassword(int originNumber, Short userId, String encryptedPassword) {
        EncryptedPasswordAndCreationDateTime encryptedPasswordAndCreationDateTime = new EncryptedPasswordAndCreationDateTime(encryptedPassword, LocalDateTime.now());

        if (this.store.containsKey(originNumber)) {
            Map<Short, EncryptedPasswordAndCreationDateTime> userIdsMap = this.store.get(originNumber);
            userIdsMap.put(userId, encryptedPasswordAndCreationDateTime);
        } else {
            Map<Short, EncryptedPasswordAndCreationDateTime> userIdToEncryptedPasswordInformationMap = new HashMap<>(){{
                put(userId, encryptedPasswordAndCreationDateTime);
            }};
            this.store.put(originNumber, userIdToEncryptedPasswordInformationMap);
        }
    }
}
