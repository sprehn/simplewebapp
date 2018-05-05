package com.firstdata.simplewebapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Spring config class which initiates the redis conenction factory.
 */
@Configuration
@EnableRedisHttpSession
@PropertySource("classpath:app.properties")
public class Config {

   
    @Value("${redis.url}")
    private String redisUrl;

    @Value("${redis.port:6379}")
    private int redisPort;
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(redisUrl, redisPort);
    }
    
    @Bean
     public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
     }

}
