package com.cici;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：cici
 * @date ：Created in 2019/9/9 16:19
 * @EnableFeignClients这个注解是是否开启openfeign
 */
@EnableDiscoveryClient
@SpringCloudApplication
@EnableFeignClients
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }


}
