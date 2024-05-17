package com.rgr.webtransferback.util.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.stream.Stream;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.test.util.ReflectionTestUtils;

public class AESCryptTest {
    private AESCrypt aesCrypt;
    private AESKeyStorage aesKeyStorageMock;
    private SecretKey secretKey;
    private IvParameterSpec ivSpec;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        aesKeyStorageMock = Mockito.mock(AESKeyStorage.class);
        aesCrypt = new AESCrypt();
        ReflectionTestUtils.setField(aesCrypt, "aesKeyStorage", aesKeyStorageMock);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        secretKey = keyGenerator.generateKey();

        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        ivSpec = new IvParameterSpec(iv);

        when(aesKeyStorageMock.loadKey()).thenReturn(secretKey);
        when(aesKeyStorageMock.loadIv()).thenReturn(ivSpec);
    }

    @Test
    void testEncryptDecrypt() throws Exception {
        String originalText = "teste encrypt";
        String encryptedText = aesCrypt.encrypt(originalText);
        String decryptedText = aesCrypt.decrypt(encryptedText);

        assertEquals(originalText, decryptedText);
    }

    @ParameterizedTest
    @MethodSource("provideMultipleStrings")
    void decrypt_ShouldDecryptAndEncrypt(String originalText) throws Exception {
        String encryptedText = aesCrypt.encrypt(originalText);
        String decryptedText = aesCrypt.decrypt(encryptedText);

        assertEquals(originalText, decryptedText);
    }

    private static Stream<String> provideMultipleStrings() {
        return Stream.of(
                "asdf",
                "asdf321",
                "Ab",
                "C9",
                "xy",
                "12",
                "ABcdEFGHijklMNOpqrsTUVWxyz",
                "!@#$%^&*()-_=+[{]}|;:',<.>/?`",
                "ThisIsALo ngStringWith30Characters",
                "short",
                "ComplexPassword123!@#",
                "01abcdeFgHIJKLMNOPQRSTUvwxy",
                "9876543210",
                "OneMoreStringWithApproximately30Chars",
                "!@#abcDEF1234567890!@#",
                "A",
                "s",
                "345678901234567890123456789012",
                "QWERTYUIOPASDFGHJKLZXCVBNM1234",
                "!@#$%^&*()-_=+[{]}|;:',<.>/?"
        );
    }
}