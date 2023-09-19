package TimeSheetApp.BackEnd.InfoValidation;

public class LoginValidator {

    public LoginValidator(){

    }
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.contains("@");
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.isEmpty() || password.length() < 8) {
            return false;
        }
        return true;
    }
}

