package com.cornerfoodmarketwebsite.configuration.utils;

public enum OriginPropertyEnum {
    ORIGIN {
        @Override
        public String toString() {
            return "origin";
        }
    },
    STORE_NAME {
        @Override
        public String toString() {
            return "storeName";
        }
    },
    IS_ALLOWED {
        @Override
        public String toString() {
            return "isAllowed";
        }
    },
    ROLE {
        @Override
        public String toString() {
            return "role";
        }
    };
}
