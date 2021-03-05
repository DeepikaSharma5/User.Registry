package com.hms.user.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class UserregistryApplication {

   @Autowired
    Environment environment;

   private final String URL = "spring.datasource.url";
   private final String USER = "spring.datasource.username";
   private final String DRIVER = "spring.datasource.driver-class-name";
   private final String PASSWORD = "spring.datasource.password";

   @Bean
    DataSource dataSource(){
       DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
       driverManagerDataSource.setUrl(environment.getProperty(URL));
       driverManagerDataSource.setUsername(environment.getProperty(USER));
       driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
       driverManagerDataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(DRIVER)));

       return driverManagerDataSource;
   }

   @Bean
    JdbcTemplate jdbcTemplate(){
       return new JdbcTemplate(dataSource());
   }

    public static void main(String[] args) {
        SpringApplication.run(UserregistryApplication.class, args);
    }

}
