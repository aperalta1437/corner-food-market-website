package com.cornerfoodmarketwebsite.business.service.utils;

import com.cornerfoodmarketwebsite.configuration.utils.ClientOriginProperties;
import com.cornerfoodmarketwebsite.configuration.utils.OriginProperties;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

// This class is a bean defined during configuration.
public class LoginRsaKeysStore {
    private Map<Long, Map<Integer, Map<RsaKeyTypeEnum, String>>> store = new HashMap<>();
    private long latestMapAccessNumber;
    private String previousMapAccessNumber;

    public LoginRsaKeysStore(HashMap<Integer, ClientOriginProperties> clientOriginProperties, int customerRsaKeySize, int administratorRsaKeySize) throws NoSuchAlgorithmException {
        latestMapAccessNumber = System.currentTimeMillis();
        RsaUtil customerRsaUtil = new RsaUtil(customerRsaKeySize);
        RsaUtil administratorRsaUtil = new RsaUtil(administratorRsaKeySize);

        Map<Integer, Map<RsaKeyTypeEnum, String>> latestKeysMap = new HashMap<>();

        clientOriginProperties.forEach((originNumber, originProperties) -> {
            Base64RsaKeyPair customerBase64RsaKeyPair = customerRsaUtil.generateBase64RsaKeyPair();
            Base64RsaKeyPair administratorBase64RsaKeyPair = administratorRsaUtil.generateBase64RsaKeyPair();

            if (originProperties.isAllowed()) {
                latestKeysMap.put(originNumber,
                        new HashMap<>(){{
                            if (originProperties.getRole() == RoleEnum.CUSTOMER) {
                                put(RsaKeyTypeEnum.PUBLIC, customerBase64RsaKeyPair.getBase64PublicKey());
                                put(RsaKeyTypeEnum.PRIVATE, customerBase64RsaKeyPair.getBase64PrivateKey());
                            } else {
                                put(RsaKeyTypeEnum.PUBLIC, administratorBase64RsaKeyPair.getBase64PublicKey());
                                put(RsaKeyTypeEnum.PRIVATE, administratorBase64RsaKeyPair.getBase64PrivateKey());
                            }
                        }}
                );
            }
        });

        this.store.put(latestMapAccessNumber, latestKeysMap);
    }

    public Base64PublicKeyInformation getBase64PublicKeyInformation(int originNumber) {
        return new Base64PublicKeyInformation(this.store.get(latestMapAccessNumber).get(originNumber).get(RsaKeyTypeEnum.PUBLIC), latestMapAccessNumber);
    }

    public String getPrivateKey(long mapAccessNumber, int originNumber) {
        return this.store.get(mapAccessNumber).get(originNumber).get(RsaKeyTypeEnum.PRIVATE);
    }
}
