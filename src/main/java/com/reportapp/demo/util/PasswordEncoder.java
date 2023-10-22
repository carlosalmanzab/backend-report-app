package com.reportapp.demo.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {

    private PasswordEncoder() {
        throw new IllegalStateException("Password utility class");
    }

    public static String encode(String password) {
        // Saltea aleatoriamente las contraseñas para hacerlas más seguras
        String salt = generateRandomSalt();
        String saltedPassword = salt + password;
        return DigestUtils.sha256Hex(saltedPassword);
    }

    public static boolean matches(String password, String encodedPassword) {
        // Verifica si la contraseña proporcionada coincide con la contraseña almacenada en la base de datos
        return encodedPassword.equals(encode(password));
    }

    private static String generateRandomSalt() {
        // Genera una sal aleatoria, tomando la hora actual
        String timestamp = String.valueOf(System.currentTimeMillis());
        return DigestUtils.sha256Hex(timestamp);
    }
}
