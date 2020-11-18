package com.ysn.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.logging.Logger;


//@EnableTransactionManagement
//@ComponentScan({"com.ysn.restapp.controller", "com.ysn.restapp.service", "com.ysn.restapp.config"})
//@EntityScan("tr.com.akgunyazilim.arm.webui.model")
//@EnableJpaRepositories({"com.ysn.restapp.dao"})
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
        logger.info("Yasin SULA RestApp");
        SpringApplication.run(RestappApplication.class, args);
    }
}
