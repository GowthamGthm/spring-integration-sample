package com.gthm.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

@SpringBootApplication
@EnableIntegration
public class SpringIntegrationSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationSampleApplication.class, args);
    }



    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(5000));
        return pollerMetadata;
    }

}