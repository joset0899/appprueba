package com.login.stats.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.login.stats.config.EmailConfig;
import com.login.stats.config.MvcConfig;
import com.login.stats.config.RepositoryConfig;
import com.login.stats.config.SecurityConfig;
import com.login.stats.config.ServiceConfig;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@Import({MvcConfig.class, RepositoryConfig.class, ServiceConfig.class, SecurityConfig.class, EmailConfig.class})
public class LoginStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginStatsApplication.class, args);
	}
}
