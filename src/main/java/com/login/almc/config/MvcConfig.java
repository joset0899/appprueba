package com.login.almc.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.login.stats.controller", "com.login.stats.advice", 
		"com.login.stats.validators", "com.login.stats.listeners"})
public class MvcConfig {

}
