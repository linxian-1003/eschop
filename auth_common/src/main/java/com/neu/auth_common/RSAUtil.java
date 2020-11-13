package com.neu.auth_common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;



/**
 * RAS工具类
 */


public class RSAUtil {

    /**
     * 读取公钥
     * @param filename
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String filename) throws Exception {
        byte[] bytes = readFile(filename);
        return getPublicKey(bytes);
    }

    /**
     * 获取公钥
     * @param bytes
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] bytes) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    /**
     * 读取私钥
     * @param filename
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String filename) throws Exception {
        byte[] bytes = readFile(filename);
        return getPrivateKey(bytes);
    }

    /**
     * 获取私钥
     * @param bytes
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] bytes) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    /**
     * 根据随机种子生产公钥和私钥并且保存到文件中
     * @param publicKeyFilePath    公钥保存路径
     * @param privateKeyFilePath   私钥保存路径
     * @param seed                 随机种子
     * @throws Exception
     */
    public static void generateKey(String publicKeyFilePath, String privateKeyFilePath, String seed) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(seed.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取公钥并写出
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        writeFile(publicKeyFilePath, publicKeyBytes);
        // 获取私钥并写出
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        writeFile(privateKeyFilePath, privateKeyBytes);
    }

    /**
     * 读取文件
     * @param fileName
     * @return
     * @throws Exception
     */
    private static byte[] readFile(String fileName) throws Exception {
        return Files.readAllBytes(new File(fileName).toPath());
    }

    /**
     * 写入文件
     * @param destPath
     * @param bytes
     * @throws IOException
     */
    private static void writeFile(String destPath, byte[] bytes) throws IOException {
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        Files.write(dest.toPath(), bytes);
    }

    public static void main(String... args) throws Exception {
        generateKey("D://pub.txt","D://pri.txt","abc");
    }


}