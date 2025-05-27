package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chat-trade")
public class AuthController {

	@GetMapping({"/", "signin"})
	public String showSigninForm() {
		return "auth/signin";
	}
	
	@PostMapping("/signin")
	public String signin(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		
		// 認証の確認
		
		return "index";
	}
	
	@GetMapping("/signup")
	public String showSignup() {
		
		// アカウント情報の登録
		return "auth/signup";
	}
	
	@PostMapping("/signup")
	public String signup(
			@RequestParam("newUsername") String newUsername,
			@RequestParam("newPassword") String newPassword) {
		
		return "redirect:signin";
	}
	
	
}
