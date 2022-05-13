package com.bithumb.tide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TideGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TideGatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("async", p -> p
//						.path("/async/*")
//						.filters(f -> f
//								.addRequestHeader("async-option", "TRUE")
//								.setPath("/{segment}"))
//						.uri("http://localhost:8081")
////				)
////				.route("sync", p -> p
////						.alwaysTrue()
////						.filters(f -> f.addRequestHeader("async-option", "FALSE"))
////						.uri("http://localhost:8081")
//				).build();
//	}
}


