package com.github.encrypt;

import org.jasypt.util.text.BasicTextEncryptor;

public class EncryptMain {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("nmyswls");
        String password = textEncryptor.encrypt("Abc5462.");
        System.out.println(password);
    }
}
