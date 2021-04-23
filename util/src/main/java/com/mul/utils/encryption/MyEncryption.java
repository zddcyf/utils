package com.mul.utils.encryption;

import com.mul.utils.MD5;

/**
 * Created by jiang on 2017-09-25.
 */

public class MyEncryption {
    private static String key = "fei89r3hjgjfJFKv@#$9rjgjfJv@#$9rjFKDJOFEVI";
    public static String decryptor(String string){
        String md5Key = MD5.MD5(key);
        byte[] b1 = Base64.decode(string);
        StringBuilder r1= new StringBuilder();
        for (int i = 0; i < b1.length; i++) {
            int a1=b1[i];
            int a2=md5Key.charAt(i%32);
            int a3=a2^a1;
            r1.append((char) a3);
        }
        try {
            return new String(Base64.decode(r1.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    public static String encryption(String string){
        String md5Key = MD5.MD5(key);
        String s=Base64.encode(string.getBytes());
//        byte[] b1 = Base64.decode(Base64.encode(string.getBytes()));
//		System.out.println(s2);
        StringBuilder r1= new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int a1=s.charAt(i);
            int a2=md5Key.charAt(i%32);
            int a3=a2^a1;
            r1.append((char) a3);
        }
        try {
            return Base64.encode(r1.toString().getBytes());
        } catch (Exception e) {
            return null;
        }
    }


}
