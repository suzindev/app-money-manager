/*
 * This source file was generated by the Gradle 'init' task
 */
package com.suzintech;

import com.suzintech.config.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(WebServerConfig.class, args);
    }
}
