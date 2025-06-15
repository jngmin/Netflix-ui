package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.demo.client")
@EnableAutoConfiguration(exclude = {
	    DataSourceAutoConfiguration.class,
	    HibernateJpaAutoConfiguration.class
	})
public class NetflixUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixUiApplication.class, args);
	}

}
