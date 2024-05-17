package com.rgr.webtransferback.util.encryption;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AESCrypt implements IEncryptor {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    @Autowired
    private AESKeyStorage aesKeyStorage;
        
    public String encrypt(String input)  {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.aesKeyStorage.loadKey(), this.aesKeyStorage.loadIv());
            byte[] cipherText = cipher.doFinal(input.getBytes());
            return Base64.getEncoder()
                .encodeToString(cipherText);    
        } catch ( InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }        
    }

    public String decrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, this.aesKeyStorage.loadKey(), this.aesKeyStorage.loadIv());
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(input));

            return new String(plainText);    

        } catch ( InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }        
    }
}