package com.spboot.cipher.controller;


import com.spboot.cipher.service.DecryptionService;
import com.spboot.cipher.service.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@CrossOrigin("*")
@RestController
@Slf4j
public class CipherController {

    private final EncryptionService encryptionService;
    private final DecryptionService decryptionService;

    public CipherController(EncryptionService encryptionService, DecryptionService decryptionService) {
        this.encryptionService = encryptionService;
        this.decryptionService = decryptionService;
    }


    @PostMapping("/encrypt")
    public ResponseEntity<?> encryptIt(@RequestBody String request) {
        log.info("Encryption  Initiated");
      return ResponseEntity.ok(encryptionService.encryptText(request));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decryptIt(@RequestBody String request) {
        log.info("Encryption  Initiated");
        return ResponseEntity.ok(decryptionService.decryptText(request));
    }
}
