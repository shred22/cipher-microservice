package com.spboot.cipher.service;


import com.spboot.cipher.config.CipherConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

@Service
@Slf4j
public class EncryptionService {

    private final Cipher encryptor;

    public EncryptionService(@Qualifier("enc") Cipher encryptor) {
        this.encryptor = encryptor;
    }

    public String encryptText(String message)
    {
        byte[] encryptedText = null;
        try {
           return Base64.encodeBase64String(encryptor.doFinal(message.getBytes(StandardCharsets.UTF_8)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
           log.error("Error Occurred : {}", e.getLocalizedMessage());
        }
        return null;
    }
}
