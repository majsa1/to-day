package nl.hhs.apep2122group1.utils;

import android.text.Editable;

import java.util.Arrays;

import nl.hhs.apep2122group1.models.Label;

public class Validators {
    public static boolean ValidateStringNotNullOrEmpty(Editable editable) {
        if (editable != null && !editable.toString().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean ValidateStringDoesNotContainWhitespace(Editable editable) {
        String str = editable.toString();
        if (str.contains("\r") || str.contains("\n") || str.contains("\t") || str.contains(" ")) {
            return false;
        }
        return true;
    }

    public static boolean ValidateStringDoesNotBeginOrEndWithWhitespace(Editable editable) {
        String str = editable.toString();
        if (!str.trim().equals(str)) {
            return false;
        }
        return true;
    }

    public static boolean ValidateMinimumLength(Editable editable, int minimumLength) {
        if (editable.toString().length() >= minimumLength) {
            return true;
        }
        return false;
    }

    public static boolean ValidateNewLabelTitleUnique(Editable editable, Label[] existingLabels) {
        String newLabelTitle = editable.toString();
        boolean labelAlreadyUsed = Arrays.stream(existingLabels).anyMatch(l -> l.getTitle().equalsIgnoreCase(newLabelTitle));
        return !labelAlreadyUsed;
    }
}

