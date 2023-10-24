package com.yt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class DraftApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(DraftApp.class,args);
    }
}
