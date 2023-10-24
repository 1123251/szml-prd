package com.yt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Hello world!
 *
 */
@SpringBootApplication

public class UserApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserApp.class,args);
    }
}
