package com.example.android.studystream.Utils;

public class TextUtils {

    public static boolean checkSpaces(String text) {
        String textAfterTrim = text.trim();
        if (textAfterTrim.length() != text.length())
            return true;
        else return false;
    }

}
