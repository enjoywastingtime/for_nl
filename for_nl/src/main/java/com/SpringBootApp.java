package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.factory.BaseRepositoryFactoryBean;

@EnableJpaRepositories(basePackages = { "com.*.dao" }, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@SpringBootApplication
public class SpringBootApp {

	@RequestMapping("/")
	public String toIndex() {
		return "pages/index.jsp";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
}