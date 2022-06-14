package nl.hhs.apep2122group1.utils;

import java.util.Arrays;

import nl.hhs.apep2122group1.models.Label;

public class Validators {
    public static boolean validateStringNotNullOrEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean validateStringDoesNotContainWhitespace(String str) {
        if (str.contains("\r") || str.contains("\n") || str.contains("\t") || str.contains(" ")) {
            return false;
        }
        return true;
    }

    public static boolean validateStringDoesNotBeginOrEndWithWhitespace(String str) {
        if (!str.trim().equals(str)) {
            return false;
        }
        return true;
    }

    public static boolean validatePasswordComplexity(String password) {
        if (password.equals("123456") || password.equalsIgnoreCase("abcdef") || password.equalsIgnoreCase("password") || password.equalsIgnoreCase("batman")) {
            return false;
        } else if (allCharactersAreTheSame(password)) {
            return false;
        } else if (password.length() < 6) {
            return false;
        }
        return true;
    }

    public static boolean validateNewLabelTitleUnique(String newLabelTitle, Label[] existingLabels) {
        boolean labelAlreadyUsed = Arrays.stream(existingLabels).anyMatch(l -> l.getTitle().equalsIgnoreCase(newLabelTitle));
        return !labelAlreadyUsed;
    }

    private static boolean allCharactersAreTheSame(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c != chars[0]) {
                return false;
            }
        }
        return true;
    }
}

