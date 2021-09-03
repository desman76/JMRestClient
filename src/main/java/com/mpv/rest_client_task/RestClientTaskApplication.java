package com.mpv.rest_client_task;

import com.mpv.rest_client_task.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class RestClientTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestClientTaskApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        communication.startCommunicate();
        communication.showResult();
    }
}
