package com.cici.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author ：cici
 * @date ：Created in 2019/9/16 16:19
 */
@SpringCloudApplication
@MapperScan("com.cici.oauth.domain")
public class Oauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }


}
