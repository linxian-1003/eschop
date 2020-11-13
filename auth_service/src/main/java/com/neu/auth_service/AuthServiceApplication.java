package com.neu.auth_service;

import com.neu.auth_service.config.SysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableConfigurationProperties(SysConfig.class)
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced  //开启负载功能
    public RestTemplate setRestTemplate(){
        return new RestTemplate();
    }

    //负载策略调整
    //方式一:在容器中注册具体负载策略[全局修改]
/*        @Bean
    public IRule setRule(){
        return new RandomRule();
    }*/

    //方式二:修改指定服务的负载策略


}