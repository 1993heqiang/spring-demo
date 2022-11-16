package com.example.springmvc.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/")
	public String index(Model model) {
		Map<String, String> userObject = new HashMap<>();
		userObject.put("name", "Jim");
		model.addAttribute("userObject", userObject);
		return "index";
	}
}
