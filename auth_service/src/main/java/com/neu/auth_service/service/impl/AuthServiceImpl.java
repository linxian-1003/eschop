package com.neu.auth_service.service.impl;

import com.neu.auth_common.JWTUtil;
import com.neu.auth_common.RSAUtil;
import com.neu.auth_service.client.MemberServiceClient;
import com.neu.auth_service.config.SysConfig;
import com.neu.auth_service.domain.Auth;
import com.neu.auth_service.service.AuthService;
import com.neu.member_service.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    MemberServiceClient memberServiceClient;

    @Autowired
    SysConfig sysConfig;


    @Override
    public String auth(Auth auth) {

        Member member = memberServiceClient.login(auth.getLoginName(),auth.getPassword());

        if(member==null){
            return null;
        }else{
            // 生成Token

            //生成密钥对
            //监听:当项目启动成功后生成
            //
            //String oriInfo, PrivateKey privateKey, int expire

            //userName=
            //loginName

            return JWTUtil.generateToken(auth.getLoginName(),sysConfig.getPrivateKey(),sysConfig.getExpire());
        }

    }

    /**
     * 根据token解析原始信息
     * @param token
     * @return
     */
    @Override
    public String info(String token) {
        try {
            return JWTUtil.getInfoFromToken(token, RSAUtil.getPublicKey(sysConfig.getPublicKeyPath()));
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 日志
            return null;
        }
    }
}
