package com.cici.tools;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringEncryptUtil {


    public static String Encrypt(String encryptMsg, String encryptMethod) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = encryptMsg.getBytes();
        try {
            if (encryptMethod == null || encryptMethod.equals("")) {
                encryptMethod = "SHA-256";
            }
            md = MessageDigest.getInstance(encryptMethod);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
