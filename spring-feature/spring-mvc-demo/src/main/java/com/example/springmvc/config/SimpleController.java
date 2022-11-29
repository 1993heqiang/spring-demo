package com.example.springmvc.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SimpleController
 *
 * @author HeQiang
 * @since 2022/11/12
 **/
@RestController
public class SimpleController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

}
