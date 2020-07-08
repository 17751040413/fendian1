package com.wowoniu.fendian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wowoniu.fendian.mapper")
public class FendianApplication {

	public static void main(String[] args) {
		SpringApplication.run(FendianApplication.class, args);
	}

}
