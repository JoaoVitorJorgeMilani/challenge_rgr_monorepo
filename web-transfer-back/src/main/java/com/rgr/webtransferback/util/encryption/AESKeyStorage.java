package com.rgr.webtransferback.util.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class AESKeyStorage {

    @Value("${aes.key.path}")
    private String aesKeyPath;

    @Value("${aes.iv.path}")
    private String aesIvPath;

    protected SecretKey loadKey() {
        try {
            if (!new File(this.aesKeyPath).exists()) {
                generateKey();
            }

            byte[] keyBytes = Files.readAllBytes(Paths.get(this.aesKeyPath));
            return new SecretKeySpec(keyBytes, "AES");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected IvParameterSpec loadIv() {
        try {
            if (!new File(this.aesIvPath).exists()) {
                generateIv();
            }
            byte[] ivBytes = Files.readAllBytes(Paths.get(this.aesIvPath));
            return new IvParameterSpec(ivBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SecretKey generateKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            
            saveKey(secretKey);
            return secretKey;
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveKey(SecretKey key) {
        try {
            Files.write(Paths.get(this.aesKeyPath), key.getEncoded());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        saveIv(ivSpec);
        return ivSpec;
    }

    private void saveIv(IvParameterSpec iv) {
        try {
            Files.write(Paths.get(this.aesIvPath), iv.getIV());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
