package TimeSheetApp.BackEnd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class InfoValidator {

    public InfoValidator() {

    }

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.contains("@");
    }

    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty() || password.length() < 8) {
            return false;
        }
        return true;
    }

    public boolean validateCpf(String cpf) {
        // Verifica se o CPF tem 11 dígitos
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);

        // Verifica se o primeiro dígito verificador está correto
        if (primeiroDigito >= 10) {
            if (Character.getNumericValue(cpf.charAt(9)) != 0) {
                return false;
            }
        } else {
            if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
                return false;
            }
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);

        // Verifica se o segundo dígito verificador está correto
        if (segundoDigito >= 10) {
            if (Character.getNumericValue(cpf.charAt(10)) != 0) {
                return false;
            }
        } else {
            if (Character.getNumericValue(cpf.charAt(10)) != segundoDigito) {
                return false;
            }
        }
        return true;
    }

    public boolean validateCellPhoneNumber(String cellPhoneNumber) {
        // Expressão regular para validar números de telefone no formato "xx xxxxxxxxx" ou "11997024653"
        String regex = "\\d{2} \\d{10}|11\\d{9}";

        if (cellPhoneNumber == null || cellPhoneNumber.isEmpty()) {
            return false;
        }
        return Pattern.matches(regex, cellPhoneNumber);
    }
    public boolean validateBirthDate(String birthDate) {
        if (birthDate == null || birthDate.length() != 8) {
            return false;
        }

        int day = Integer.parseInt(birthDate.substring(0, 2));
        int month = Integer.parseInt(birthDate.substring(2, 4));
        int year = Integer.parseInt(birthDate.substring(4, 8));

        // Verifica se os valores de dia, mês e ano são válidos
        if (month < 1 || month > 12 || day < 1 || day > 31 || year < 1900) {
            return false;
        }

        // Verifica se a data não está no futuro (aniversário não pode ser no futuro)
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(birthDate);
            Date currentDate = new Date();
            if (parsedDate.after(currentDate)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        // Se chegou até aqui, a data é válida
        return true;
    }
}


