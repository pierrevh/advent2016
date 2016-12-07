package org.pvhees.advent.day5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityDoor {

    public static String getPasswordFor(String doorId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        char[] password = new char[8];

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        int index = 0;
        int passwordCharactersFound = 0;
        while (passwordCharactersFound < 8) {
            String doorIdAndIndex= doorId + index;
            String hash = byteToString(md5.digest(doorIdAndIndex.getBytes("UTF-8")));
            if (hash.startsWith("00000")) {
                password[passwordCharactersFound] = hash.charAt(5);
                passwordCharactersFound++;
            }
            index++;
        }

        return new String(password);
    }

    //TODO; remove duplication
    public static String getPasswordForV2(String doorId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        char[] password = new char[] {' ',' ',' ',' ',' ',' ',' ',' '};

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        int index = 0;
        int passwordCharactersFound = 0;
        while (passwordCharactersFound < 8) {
            String doorIdAndIndex= doorId + index;
            String hash = byteToString(md5.digest(doorIdAndIndex.getBytes("UTF-8")));
            if (hash.startsWith("00000")) {
                char pswPosition = hash.charAt(5);
                if (pswPosition >= '0' && pswPosition <= '7') {
                    int pswPosIndex = Integer.parseInt(hash.substring(5, 6));
                    if (password[pswPosIndex] == ' ') {
                        password[pswPosIndex] = hash.charAt(6);
                        passwordCharactersFound++;
                        System.out.println("Found " + hash.charAt(6) + " at " + pswPosIndex + "(nr " + passwordCharactersFound + ")");
                    }
                }
            }
            index++;
        }

        return new String(password);
    }

    private static String byteToString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
}
