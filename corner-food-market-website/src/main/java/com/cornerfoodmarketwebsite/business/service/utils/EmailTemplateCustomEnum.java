package com.cornerfoodmarketwebsite.business.service.utils;

public class EmailTemplateCustomEnum {
    public static class TFA_CODE {
        private static final String template = "Your Two Factor Authentication code is: %s";

        public static String getEmailContent(String tfaCode) {
            return String.format(template, tfaCode);
        }
    }

    public static class NEW_ADMIN_SIGNUP_URL {
        private static final String template = "Sign up as a new administrator using the following link: %s/admin/new-admin-signup/email/%s/uuid/%s";

        public static String getEmailContent(String originUrl, String newAdministratorEmail, String uuid) {
            return String.format(template, originUrl, newAdministratorEmail, uuid);
        }
    }

    public static class NEW_CUSTOMER_EMAIL_VERIFICATION_URL {
        private static final String template = "Confirm your email by clicking the following link: %s/admin/new-admin-signup/%s";

        public static String getEmailContent(String requestUrl, String uuid) {
            return String.format(template, requestUrl, uuid);
        }
    }
}
