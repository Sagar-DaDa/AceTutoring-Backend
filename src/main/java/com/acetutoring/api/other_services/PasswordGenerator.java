package com.acetutoring.api.other_services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordGenerator {
    private static String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generatePassword() {
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            passwordBuilder.append(allowedChars.charAt(randomIndex));
        }
        return passwordBuilder.toString();
    }

    public static String generatePassword(int length) {
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            passwordBuilder.append(allowedChars.charAt(randomIndex));
        }
        return passwordBuilder.toString();
    }
}
