package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/furukari")
public class ItemController {

	@Autowired
	HttpSession session;

	@Autowired
	User user;

	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/item")
	public String index(Model model) {

		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		// 管理者ユーザは管理者画面へ遷移
		if (username.equals("root")) {
			return "redirect:/furukari/admin";
		}
		model.addAttribute("username", username);

		// itemsテーブルから商品を全て抽出
		List<Item> itemList = itemRepository.findAll();
		model.addAttribute("itemList", itemList);
		System.out.println(itemList.get(0).getImgPath());

		return "main/itemList";
	}

	@GetMapping("/item/{id}")
	public String confirm(
			@PathVariable("id") Integer id,
			Model model) {

		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", username);

		// idから指定商品を抽出
		Item item = itemRepository.findByid(id);
		model.addAttribute("item", item);
		return "main/itemDetail";
	}

	@GetMapping("/item/purchase")
	public String purchase(Model model) {

		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", username);

		return "main/itemPurchase";
	}

}
