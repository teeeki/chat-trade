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
import com.example.demo.repository.UserRepository;

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

	@Autowired
	UserRepository userRepository;

	static Integer id;

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

		return "main/itemList";
	}

	@GetMapping("/item/{id}")
	public String confirm(
			@PathVariable("id") Integer tmpId,
			Model model) {

		id = tmpId;
		final String loginedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", loginedUsername);

		// idから指定商品を抽出
		Item item = itemRepository.findByid(id);
		model.addAttribute("item", item);
		model.addAttribute("username", loginedUsername);

		// ユーザ名のみユーザIDから取得
		User user = userRepository.findById(item.getId()).get();
		String username = user.getUsername();
		model.addAttribute("username", username);

		return "main/itemDetail";
	}

	@GetMapping("/item/purchase")
	public String purchase(Model model) {

		itemRepository.deleteAllById(id);
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", username);

		return "main/itemPurchase";
	}

}
