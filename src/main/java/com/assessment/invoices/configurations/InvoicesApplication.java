package com.assessment.invoices.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.assessment.invoices.controllers", "com.assessment.invoices.services"})
@EnableJpaRepositories(basePackages = { "com.assessment.invoices.repositories" })
@EntityScan("com.assessment.invoices")
public class InvoicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoicesApplication.class, args);
	}
}
