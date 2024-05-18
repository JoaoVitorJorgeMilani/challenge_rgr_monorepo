package com.rgr.webtransferback.util.encryption;

import java.util.UUID;

public interface  IEncryptor {
    public String encrypt(String input);
    public String decrypt(String input);
    public String encryptUuid(UUID uuid);
    public UUID decryptUuid(String encryptedUuid);
    
}
