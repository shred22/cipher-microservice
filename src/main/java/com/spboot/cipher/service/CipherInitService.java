package com.spboot.cipher.service;

import com.spboot.cipher.config.CipherConfigurationProperties;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class CipherInitService {

    private final CipherConfigurationProperties properties;

    public CipherInitService(CipherConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Qualifier("enc")
    public Cipher encryptionBean(Pair<PrivateKey,PublicKey> keyPair) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance(properties.getAlgo());
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getRight());
        return cipher;
    }

    @Bean
    @Qualifier("dec")
    public Cipher decryptionBean(Pair<PrivateKey,PublicKey> keyPair) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance(properties.getAlgo());
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getLeft());
        return cipher;
    }

    @Bean
    public Pair<PrivateKey,PublicKey> keyPair() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {

        byte[] keyBytes = Files.readAllBytes(new File("KeyPair/privateKey").toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");


        byte[] pubKeyBytes = Files.readAllBytes(new File("KeyPair/publicKey").toPath());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKeyBytes);


        return Pair.of(kf.generatePrivate(spec),  kf.generatePublic(keySpec));
    }


}
