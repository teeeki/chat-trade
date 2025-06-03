package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.CustomUserDetailService;

import jakarta.servlet.http.HttpSession;

/**
 * 認証にまつわるログイン、アカウント登録を行うクラス
 */
@Controller
@RequestMapping("/furukari")
public class UserController {

	private final CustomUserDetailService userDetailService;

	@Autowired
	HttpSession session;

	@Autowired
	User user;

	public UserController(CustomUserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	@GetMapping({ "/", "/signin", "/logout" })
	public String showSigninForm() {
		session.invalidate();
		return "auth/signin";
	}

	@GetMapping("/signup")
	public String showSignup(Model model) {
		model.addAttribute("user", new User());
		return "auth/signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute User user) {
		// @ModelAttributeとテンプレートsignup内が紐づく
		userDetailService.registerUser(user);

		return "redirect:/auth/signin";
	}

}
