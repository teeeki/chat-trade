package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.repository.ItemRepository;

@Controller
@RequestMapping("/furukari")
public class ItemController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/item")
	public String index(Model model) {

		// itemsテーブルから商品を全て抽出
		List<Item> itemList = itemRepository.findAll();
		model.addAttribute("itemList", itemList);

		return "main/itemList";
	}

}
