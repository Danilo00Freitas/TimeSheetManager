package TimeSheetApp.BackEnd.PasswordManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class PasswordManager {
    private String passwordToHash;
    private String passwordTocheck;
    private static final int saltLenght = 16;

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[saltLenght];
        random.nextBytes(salt);
        return salt;
    }

    public String generateSaltedPassword(String password) throws NoSuchAlgorithmException {
        byte[] salt = generateSalt();
        String saltedPassword = password + Base64.getEncoder().encodeToString(salt);
        return generatePassword(saltedPassword.toCharArray());
    }

    public String generatePassword(char[] password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(new String(password).getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public boolean verifyPassword(String enteredPassword, String storedPasswordHash) throws NoSuchAlgorithmException {
        // Recuperar o salt da senha armazenada
        byte[] salt = Base64.getDecoder().decode(storedPasswordHash.substring(64)); // Assumindo que os primeiros 64 caracteres são o hash

        // Concatenar o salt com a senha inserida pelo usuário
        String saltedPassword = enteredPassword + Base64.getEncoder().encodeToString(salt);

        // Calcular o hash da senha inserida pelo usuário com o salt
        String enteredPasswordHash = generatePassword(saltedPassword.toCharArray());

        // Comparar o hash da senha inserida pelo usuário com o hash armazenado no banco de dados
        return enteredPasswordHash.equals(storedPasswordHash);
    }


}
