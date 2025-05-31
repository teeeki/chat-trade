package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/furukari")
public class OrderController {

	@Autowired
	HttpSession session;

	@Autowired
	User account;

	//	@GetMapping("/order")
	//	public String confirm(
	//			@PathVariable("itemId") Integer itemId) {
	//
	//		return "orderConfirm";
	//	}

}
