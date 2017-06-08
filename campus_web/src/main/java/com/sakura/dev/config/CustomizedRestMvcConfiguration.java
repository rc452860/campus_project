package com.sakura.dev.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * Created by rc452 on 2017/6/4.
 */
@Component
public class CustomizedRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setBasePath("/api");
	}
}