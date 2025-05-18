package com.sanofi.pharma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class PharmaSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmaSystemApplication.class, args);
    }

}
