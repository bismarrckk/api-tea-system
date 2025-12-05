package com.teafarm.production.util;

import java.security.SecureRandom;

public class VerificationCodeUtil {
	private static final String DIGITS = "0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return sb.toString();
    }
}
