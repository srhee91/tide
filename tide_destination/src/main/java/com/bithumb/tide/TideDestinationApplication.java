package com.bithumb.tide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TideDestinationApplication {
	public static void main(String[] args) {
		SpringApplication.run(TideDestinationApplication.class, args);
	}
}
