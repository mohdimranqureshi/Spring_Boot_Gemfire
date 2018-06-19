package com.dish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dish.controller.HelloController;

@EnableGemfireRepositories
@EnableTransactionManagement
@SpringBootApplication
public class GemfireBootApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(GemfireBootApplication.class, args);
		HelloController ctx = context.getBean(HelloController.class);
		ctx.startGemfireRegion();
	//	context.close();
	}
}
