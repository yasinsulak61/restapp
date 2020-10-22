package com.ysn.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;

import java.util.logging.Logger;

@SpringBootApplication
@Configuration
public class RestappApplication extends SpringBootServletInitializer {
    private static Logger logger = Logger.getLogger(RestappApplication.class.getName());

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RestappApplication.class);
    }

    public static void main(String[] args) throws Exception {

        logger.info("Spring Versiyon " + SpringVersion.getVersion());
        logger.info("Yasin SULAK RestApp");
        SpringApplication.run(RestappApplication.class, args);
    }
}
