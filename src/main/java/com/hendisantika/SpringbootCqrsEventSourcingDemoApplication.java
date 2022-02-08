package com.hendisantika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableJpaAuditing
@EnableTransactionManagement
public class SpringbootCqrsEventSourcingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCqrsEventSourcingDemoApplication.class, args);
    }

}
