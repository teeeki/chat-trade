package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Account;

@Controller
@RequestMapping("/furukari")
public class OrderController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	//	@GetMapping("/order")
	//	public String confirm(
	//			@PathVariable("itemId") Integer itemId) {
	//
	//		return "orderConfirm";
	//	}

}
