package com.example.demompqrcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemoMpQrcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMpQrcodeApplication.class, args);
    }

}
