package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
@RequestMapping("/furukari")
public class UserController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@GetMapping({ "/", "/signin" })
	public String showSigninForm() {
		return "auth/signin";
	}

	@PostMapping("/signin")
	public String signin(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {

		// 認証の確認（あとで）
		account.setName(username);

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
