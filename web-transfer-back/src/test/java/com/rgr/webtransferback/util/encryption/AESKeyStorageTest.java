package com.rgr.webtransferback.util.encryption;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;


public class AESKeyStorageTest {

    private Path tempDir;
    private Object aesKeyStorage;

    @BeforeEach
    void setUp() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
        tempDir = Files.createTempDirectory("test-aes");

        Class<?> clazz = Class.forName("com.rgr.webtransferback.util.encryption.AESKeyStorage");
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        aesKeyStorage = constructor.newInstance();

        // Define os campos aesKeyPath e aesIvPath usando ReflectionTestUtils
        String keyPath = tempDir.resolve("aes.key").toString();
        String ivPath = tempDir.resolve("aes.iv").toString();
        
        ReflectionTestUtils.setField(aesKeyStorage, "aesKeyPath", keyPath);
        ReflectionTestUtils.setField(aesKeyStorage, "aesIvPath", ivPath);
    }

    private Object invokeProtectedMethod(Object obj, String methodName, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = obj.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(obj, args);
    }


    @Test
    void loadIv_ShouldGenerateAndLoadIv() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IvParameterSpec iv = (IvParameterSpec) invokeProtectedMethod(aesKeyStorage, "loadIv");
        
        byte[] ivBytes = Files.readAllBytes(tempDir.resolve("aes.iv"));

        assertNotNull(iv);
        assertArrayEquals(iv.getIV(), ivBytes);
    }

    @Test
    void loadKey_ShouldGenerateAndLoadKey() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SecretKey originalKey = (SecretKey) invokeProtectedMethod(aesKeyStorage, "loadKey");
        byte[] keyBytes = originalKey.getEncoded();
        Files.write(tempDir.resolve("aes.key"), keyBytes);

        SecretKey loadedKey = (SecretKey) invokeProtectedMethod(aesKeyStorage, "loadKey");
        assertNotNull(loadedKey);
        assertArrayEquals(originalKey.getEncoded(), loadedKey.getEncoded());
    }

    @Test
    void loadIv_ShoultLoadExistingIv() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SecretKey originalKey = (SecretKey) invokeProtectedMethod(aesKeyStorage, "loadKey");
        byte[] keyBytes = originalKey.getEncoded();
        Files.write(tempDir.resolve("aes.key"), keyBytes);

        SecretKey loadedKey = (SecretKey) invokeProtectedMethod(aesKeyStorage, "loadKey");
        assertNotNull(loadedKey);
        assertArrayEquals(originalKey.getEncoded(), loadedKey.getEncoded());
    }

    @Test
    void loadKey_ShouldLoadExistingKey() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SecretKey originalKey = (SecretKey) invokeProtectedMethod(aesKeyStorage, "loadKey");
        byte[] keyBytes = originalKey.getEncoded();
        Files.write(tempDir.resolve("aes.key"), keyBytes);

        SecretKey loadedKey = (SecretKey) invokeProtectedMethod(aesKeyStorage, "loadKey");
        assertNotNull(loadedKey);
        assertArrayEquals(originalKey.getEncoded(), loadedKey.getEncoded());
    }
}