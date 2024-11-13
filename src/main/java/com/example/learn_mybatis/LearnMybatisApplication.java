package com.example.learn_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.learn_mybatis.repositories")
public class LearnMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnMybatisApplication.class, args);
	}

}
