package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

/**
 * 出品する処理を担当
 */
@Controller
@RequestMapping("/furukari")
public class ItemSellController {

    @Autowired
    HttpSession session;

    @Autowired
    ItemRepository itemRepository;

    /**
     * 出品フォームを表示
     * @param param
     * @return
     */
    @GetMapping("/item/sell")
    public String itemSellForm() {
        return "sell/itemListForm";
    }

    @PostMapping("/item/sell")
    public String itemSell(
            @RequestParam("name") String name,
            @RequestParam("price") Integer price,
            @RequestParam("abst") String abst,
            @RequestParam("description") String description) {

        // ログイン中のユーザID
        // Integer userId = null;

        Item item = new Item(
                // userId,
                3,
                name,
                price,
                abst,
                description);

        itemRepository.save(item);

        return "sell/itemSellConfirm";
    }

}
