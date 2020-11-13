package com.neu.auth_service.config;


import com.neu.auth_common.RSAUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

//@Component
//@PropertySource("application.yml")
@ConfigurationProperties(prefix = "neu.auth")
public class SysConfig {


    private int expire;  //从配置文件中加载
    private String publicKeyPath;
    private String privateKeyPath;
    private String seek;

    private PrivateKey privateKey;
    private PublicKey publicKey;


    /**
     * 当容器创建该对象的时候会触发该方法
     */
    @PostConstruct
    public void createKeyPair(){
        //生成密钥对
        try {
            RSAUtil.generateKey(publicKeyPath,privateKeyPath,seek);
            privateKey = RSAUtil.getPrivateKey(privateKeyPath);
            publicKey = RSAUtil.getPublicKey(publicKeyPath);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 日志处理
        }
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getSeek() {
        return seek;
    }

    public void setSeek(String seek) {
        this.seek = seek;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
