package models.entities.domain.registro;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Hashing {
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public String hashear(String contraseña) {
        // Generar una nueva sal para el usuario
        byte[] salt = generateSalt();

        // Hashear la contraseña con la sal
        return hashPassword(contraseña, salt);
        //EN REALIDAD SE DEBE GUARDAR EN LA BD EL HASH Y EL SALT TAMBIEN
    }

    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Agregar la sal a la contraseña antes de hashear
            md.update(salt);

            // Hashear la contraseña
            byte[] hashedPassword = md.digest(password.getBytes());

            // Convertir el hash y la sal a una representación en Base64 para almacenar en la base de datos

            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    public static boolean verificarContraseña(String contraseñaIngresada, String hashAlmacenado, String salt) {
            // Decodificar la sal y el hash almacenado desde Base64
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            byte[] hashAlmacenadoBytes = Base64.getDecoder().decode(hashAlmacenado);

            // Hashear la contraseña ingresada con la sal almacenada
            String hashIngresado = hashPassword(contraseñaIngresada, saltBytes);

            // Comparar el hash calculado con el hash almacenado
            return hashIngresado.equals(hashAlmacenado);
        }

    }

