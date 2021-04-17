package com.spboot.cipher.service;

import com.spboot.cipher.config.CipherConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class DecryptionService {

    private final Cipher decryptor;

    public DecryptionService(@Qualifier("dec")Cipher decryptor) {
        this.decryptor = decryptor;
    }

    public String decryptText(String message) {
        try {
           return new String(decryptor.doFinal(Base64.decodeBase64(message)), StandardCharsets.UTF_8);

        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
