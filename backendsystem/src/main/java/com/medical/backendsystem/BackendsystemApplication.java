package com.medical.backendsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.medical.backendsystem.configs.RsaKeyConfig;

@EnableConfigurationProperties(RsaKeyConfig.class)
@SpringBootApplication
public class BackendsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendsystemApplication.class, args);
	}
	
}
