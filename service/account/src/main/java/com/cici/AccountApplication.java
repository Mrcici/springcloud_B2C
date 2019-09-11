package com.cici;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：cici
 * @date ：Created in 2019/9/9 16:19
 */
@EnableDiscoveryClient
@SpringCloudApplication
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }


}
