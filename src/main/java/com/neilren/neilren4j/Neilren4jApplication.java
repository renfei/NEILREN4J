package com.neilren.neilren4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neilren.neilren4j.dao")
public class Neilren4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neilren4jApplication.class, args);
	}
}
