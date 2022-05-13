package com.bithumb.tide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableFeignClients
@EnableAspectJAutoProxy
@SpringBootApplication
public class TideSourceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TideSourceApplication.class, args);
	}
}
