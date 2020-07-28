package com.self.eureka.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xiaohe
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class EurekaSpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSpringJpaApplication.class, args);
    }

}
