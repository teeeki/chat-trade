package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat-trade")
public class UserController {

	@GetMapping("/")
	public String index() {
		return "login";
	}
	
}
