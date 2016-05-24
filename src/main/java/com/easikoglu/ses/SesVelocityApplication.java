package com.easikoglu.ses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringMongoConfig.class)
public class SesVelocityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SesVelocityApplication.class, args);
	}
}
