package javaClasses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public Validation() {
    }

    public boolean val_email(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(email);

        if (email.matches(regex)) {
//            System.out.println(email + " : " + matcher.matches());
            System.out.println("البريد الالكتروني  صحيح");
            return true;
        } else {
            System.out.println("بريد الكتروني غير صحيح ");
            return false;
        }

    }

    public boolean isRequired(String value) {
        if ((value == null) || (value == "")) {
            System.out.println("الحقل مطلوب");
            return false;
        } else {
            return true;
        }

    }

    public boolean val_passwprd(String password) {

        if ((password == null) || (password == "")) {
            System.out.println("الحقل مطلوب");
            return false;
        } else {
            if ((password.length() > 5) && (password.length() < 25)) {
                return true;
            }
        }
        return false;
    }

    public boolean val_name(String name) {

        String regex = "^[a-zA-Z\\\\s]*$";
        if (name.matches(regex) && (name.length() > 2) && (name.length() < 25)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean val_phoneNumber(String phoneNumber) {

        String regex = "^(?:((?=\\(.*\\).*\\-)|"
                + "(?!.*\\()(?!.*\\)))"
                + "\\(?|091|092|094\\)?"
                + "(((?<=[)])|[\\-\\s])"
                + "(?=.*\\-)|\\.(?=.*\\.)"
                + "|(?<=^)|(?=[0-9]+$)))"
                + "?[0-9]{3}[\\s.-]?[0-9]{4}$";

        if (phoneNumber.matches(regex) && (phoneNumber.length() >= 10) && (phoneNumber.length() < 25)) {
            return true;
        } else {
            return false;
        }
    }

}
//        } else {
//            if ((phoneNumber.length() >= 10) && (phoneNumber.length() < 25)) {
//                return true;
//            }
