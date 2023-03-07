package com.Geekster.ChatApplication.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidations {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber){
        String phoneNumberRegex = "(^$|[0-9]{10})";
        Pattern pat = Pattern.compile(phoneNumberRegex);
        return pat.matcher(phoneNumber).matches();
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(password).matches();
    }
}
