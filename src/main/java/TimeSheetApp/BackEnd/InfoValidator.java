package TimeSheetApp.BackEnd;

import TimeSheetApp.BackEnd.SystemIntegration.CepInformation;
import TimeSheetApp.BackEnd.SystemIntegration.PersonalDataInformation;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
        // Expressão regular para validar números de telefone com DDD brasileiro
        String regex = "^(\\d{11}|\\d{12})$"; // Aceita 11 ou 12 dígitos

        if (cellPhoneNumber == null || cellPhoneNumber.isEmpty()) {
            return false;
        }

        if (Pattern.matches(regex, cellPhoneNumber)) {

            String ddd = cellPhoneNumber.substring(0, 2);

            String[] dddsValidos = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27",
                    "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47",
                    "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69",
                    "71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89",
                    "91", "92", "93", "94", "95", "96", "97", "98", "99"};

            if (Arrays.asList(dddsValidos).contains(ddd)) {
                return true; // O número de telefone é válido
            }
        }

        return false; // O número de telefone não corresponde ao padrão brasileiro
    }

    public boolean validateBirthDate(String birthDate) {
        if (birthDate == null || birthDate.length() != 8) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(birthDate);
            Date currentDate = new Date();

            // Verifique se a data de aniversário não está no futuro
            if (parsedDate.after(currentDate)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public boolean validatePDRSfields(PersonalDataInformation personalDataInformation){

        if (personalDataInformation.getCpf().isEmpty() ||
                personalDataInformation.getNome().isEmpty() ||
                personalDataInformation.getSetor().isEmpty() ||
                personalDataInformation.getCargo().isEmpty() ||
                personalDataInformation.getSuperior().isEmpty() ||
                personalDataInformation.getRotinaDeTrabalho().isEmpty() ||
                personalDataInformation.getGenero().isEmpty() ||
                personalDataInformation.getTelefone().isEmpty() ||
                personalDataInformation.getDataDeNascimento().isEmpty() ||
                personalDataInformation.getCpf() == null ||
                personalDataInformation.getNome() == null||
                personalDataInformation.getSetor() == null||
                personalDataInformation.getCargo() == null||
                personalDataInformation.getSuperior() == null ||
                personalDataInformation.getRotinaDeTrabalho() == null ||
                personalDataInformation.getGenero() == null||
                personalDataInformation.getTelefone() == null||
                personalDataInformation.getDataDeNascimento() == null)
        {
            return false;
        }else{
            return true;
        }
    }
    public boolean validateADRSfield(CepInformation cepInformation) {

        if (cepInformation.getCep().isEmpty() ||
                cepInformation.getBairro().isEmpty() ||
                cepInformation.getNumero().isEmpty() ||
                cepInformation.getLogradouro().isEmpty() ||
                cepInformation.getLocalidade().isEmpty() ||
                cepInformation.getUf().isEmpty() ||
                cepInformation.getCep() == null ||
                cepInformation.getBairro() == null  ||
                cepInformation.getNumero() == null  ||
                cepInformation.getLogradouro() == null  ||
                cepInformation.getLocalidade() == null ||
                cepInformation.getUf() == null ) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateNumberField(String number){
        try {
            int num = Integer.parseInt(number);
            return true;
        }catch(Exception exception){
            return false;
        }
    }

    public boolean validateNameField(String name){
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        String regex = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$";
        return name.matches(regex);
    }

    public String pdiFinalValidation(PersonalDataInformation pdi){
        StringBuilder mensagensErro = new StringBuilder();

        if (!validatePDRSfields(pdi)) {
            mensagensErro.append("Erro: Você não preencheu todos os campos!\n");
        }

        if (!validateCpf(pdi.getCpf())) {
            mensagensErro.append("Erro: Insira um CPF válido\n");
        }

        if (!validateNameField(pdi.getNome())) {
            mensagensErro.append("Erro: Insira um nome válido!\n");
        }

        if (!validateNameField(pdi.getSuperior())) {
            mensagensErro.append("Erro: Insira um nome válido para seu superior\n");
        }

        if (!validateCellPhoneNumber(pdi.getTelefone())) {
            mensagensErro.append("Erro: Insira um número de telefone válido!\n");
        }

        if (!validateBirthDate(pdi.getDataDeNascimento())) {
            mensagensErro.append("Erro: Insira uma data de nascimento válida!\n");
        }

        return mensagensErro.toString();
    }


}


