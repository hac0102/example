package com.example.hac.example.util;

import com.example.hac.example.exception.CipherException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CipherUtil {
    private static CipherUtil instance;

    @Value("${cipherutil.secretkey}")
    private String secretKey;
//
    @Value("${cipherutil.algorithm}")
    private String algorithm;

//    private Environment env;

//    private final String secretKey = "89af67339730d38416c17f1b6958d290";
//    private final String algorithm = "AES/CBC/PKCS5Padding";
    private final String iv;
    private final Key keySpec;

    public CipherUtil() {
        log.info("secretKey :: {}", secretKey);
        log.info("algorithm :: {}", algorithm);

        this.iv = this.secretKey.substring(0, 16);
        log.info("iv :: {}", iv);
        log.info("privateKey :: {}", secretKey);

        byte[]keyBytes = new byte[16];
        byte[]b = secretKey.getBytes(StandardCharsets.UTF_8);
        int len = b.length;
        if(len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        this.keySpec = new SecretKeySpec(keyBytes, "AES");
    }

    public static CipherUtil getInstance() {
        if(instance== null) {
            synchronized(CipherUtil.class) {
                instance= new CipherUtil();
                log.info("new CipherUtil!!");
            }
        }
        return instance;
    }

    public synchronized String encrypt(String str)throws CipherException {
        try{
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, this.keySpec, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));

            byte[]encrypted = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));

            return new String(Base64.encodeBase64(encrypted));

        }catch(NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new CipherException("encrypt Exception", e.getCause());
        }
    }

    public synchronized String decrypt(String str)throws CipherException{
        try{
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, this.keySpec, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));

            byte[]decoded = Base64.decodeBase64(str.getBytes());

            return new String(cipher.doFinal(decoded), StandardCharsets.UTF_8);

        }catch(NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new CipherException("decrypt Exception", e.getCause());
        }
    }
}
