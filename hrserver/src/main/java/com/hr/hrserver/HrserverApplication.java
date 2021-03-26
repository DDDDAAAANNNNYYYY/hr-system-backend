package com.hr.hrserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@SpringBootApplication
public class HrserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrserverApplication.class, args);
    }

}
