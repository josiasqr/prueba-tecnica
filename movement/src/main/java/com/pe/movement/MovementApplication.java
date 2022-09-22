package com.pe.movement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MovementApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovementApplication.class, args);
	}
}
