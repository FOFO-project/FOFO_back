package com.fofo.core.support.util;

import com.fofo.core.support.error.CoreApiException;
import com.fofo.core.support.error.CoreErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Component
public class AesUtil {

    private static final Logger log = LoggerFactory.getLogger(AesUtil.class);
    private static String aesKey;

    @Value("${aes.key}")
    private void setAesKey(String value) {
        aesKey = value;
    }

    public static String encrypt(String text) {
        try{
            byte[] keyByte = Base64.getDecoder().decode(aesKey);
            byte[] ivByte = Arrays.copyOfRange(keyByte, 0, 16);

            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");

            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(ivByte);

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch(Exception e){
            log.error(e.getMessage());
            throw new CoreApiException(CoreErrorType.AES_ENCRYPT_ERROR);
        }
    }

    public static String decrypt(String cipherText) {

        try{
            byte[] keyByte = Base64.getDecoder().decode(aesKey);
            byte[] ivByte = Arrays.copyOfRange(keyByte, 0, 16);

            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);

            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new CoreApiException(CoreErrorType.AES_DECRYPT_ERROR);
        }
    }
}
