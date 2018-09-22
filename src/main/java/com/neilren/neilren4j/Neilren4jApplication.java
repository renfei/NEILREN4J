package com.neilren.neilren4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.neilren.neilren4j.dao")
@EnableAsync
@EnableCaching
public class Neilren4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neilren4jApplication.class, args);
	}
}
