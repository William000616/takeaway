package com.hjx.takeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class TakeoutSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TakeoutSpringbootApplication.class, args);
	}

	//项目启动
	@GetMapping
	public String hello(){
		return "success!";
	}

}
