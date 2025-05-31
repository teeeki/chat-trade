package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/furukari")
public class UserController {

	@Autowired
	HttpSession session;

	@Autowired
	User account;

	@GetMapping({ "/", "/signin", "/logout" })
	public String showSigninForm() {
		session.invalidate();
		return "auth/signin";
	}

	@PostMapping("/signin")
	public String signin(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {

		// 認証の確認（あとで）
		account.setUsername(username);

		//		return "main/itemList";
		return "redirect:/furukari/item";
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
