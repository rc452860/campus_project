package com.sakura.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by rc452 on 2017/5/21.
 */

@Configuration
@EnableSpringHttpSession
public class SessionConfig {

//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }

    @Bean
    SessionRepository<ExpiringSession> inmemorySessionRepository() {
        return new MapSessionRepository();
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new CustomSessionStrategy();
    }

//    @Bean
//    HttpSessionStrategy httpSessionStrategy() {
//        HeaderHttpSessionStrategy sessionStrategy = new HeaderHttpSessionStrategy();
//        sessionStrategy.setHeaderName("auth_id");
//        return sessionStrategy;
//
//    }
}
