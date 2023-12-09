/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encriptado;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author luisn
 */
public class Encriptado {
    public static String encriptaContrase�a(String contrase�a) throws NoSuchAlgorithmException{


                // Crea una instancia del algoritmo de hash SHA-256
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                // Convierte el password a bytes y calcula el hash
                byte[] hashedPassword = md.digest(contrase�a.getBytes());

                // Convierte el hash a una cadena hexadecimal
                StringBuilder sb = new StringBuilder();
                for (byte b : hashedPassword) {
                    sb.append(String.format("%02x", b));
                }
                String hashedPasswordHex = sb.toString();

                // hashedPasswordHex contiene el hash en formato hexadecimal

                 contrase�a=hashedPasswordHex;

                 return contrase�a;
            }
}
