package com.sakura.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by rc452 on 2017/6/6.
 */
@Configuration
class CustomRestMvcConfiguration {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/api");
            }
        };
    }
}