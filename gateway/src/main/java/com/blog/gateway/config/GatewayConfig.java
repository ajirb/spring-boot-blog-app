package com.blog.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blog.gateway.filters.JwtFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("auth",r->r.path("/users/**").filters(f ->f.filter(jwtFilter)).uri("lb://blog-user"))
				.route("blog",r->r.path("/blog/**").filters(f -> f.filter(jwtFilter)).uri("lb://blog-service")).build();
	}
}
