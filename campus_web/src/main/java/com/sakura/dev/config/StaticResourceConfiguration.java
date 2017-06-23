package com.sakura.dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by rc452 on 2017/6/22.
 */
@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/upload/**")) {
			registry.addResourceHandler("/upload/**").addResourceLocations(
					"file:upload/");
		}
	}
}
