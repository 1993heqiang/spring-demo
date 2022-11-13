package com.example.springmvc.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GreetingController
 *
 * @author HeQiang
 * @since 2022/11/13
 **/
@RestController
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting() {
		return "Greeting";
	}

}
