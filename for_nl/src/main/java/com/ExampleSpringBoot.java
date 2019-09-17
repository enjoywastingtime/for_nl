package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.factory.BaseRepositoryFactoryBean;

@RestController
@EnableJpaRepositories(basePackages={"com.*.dao"},repositoryFactoryBeanClass=BaseRepositoryFactoryBean.class)
@SpringBootApplication
public class ExampleSpringBoot {
	@RequestMapping("/home")
    String home() {
        return "Hello World!";
    }
	
    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringBoot.class, args);  
    }
}