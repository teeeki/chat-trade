package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

/**
 * 出品する処理を担当
 */
@Controller
@RequestMapping("/furukari")
public class ItemSellController {

    @Autowired
    User loginedUser;

    @Autowired
    HttpSession session;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

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
            @RequestParam("description") String description,
            @RequestParam("img") MultipartFile img) {

        // ログイン中のユーザIDを取得
        final String loginedName = SecurityContextHolder.getContext().getAuthentication().getName();
        loginedUser = userRepository.findByUsername(loginedName).get();
        Integer userId = loginedUser.getId();

        // 画像を取得

        // String imgName = randomFileName();
        // System.out.println("--------------------------------");
        // System.out.println(imgName);
        // System.out.println("--------------------------------");
        String imgPath = null;
        try {
            // ファイル名
            String imgName = img.getOriginalFilename();
            System.out.println("--------------------------------");
            System.out.println(imgName);
            // 保存先パス
            imgPath = "/item_img/" + imgName;
            // 画像ファイルをバイナリデータとして取得
            byte[] content = img.getBytes();
            // 保存
            // Files.write(Paths.get(imgPath), content);
            Files.write(Paths.get("src/main/resources/static/item_img/" + imgName), content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(imgPath);
        Item item = new Item(
                userId,
                name,
                price,
                abst,
                description,
                imgPath);

        itemRepository.save(item);

        return "sell/itemSellConfirm";
    }

    public String randomFileName() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        int length = 10; // 生成する文字列の長さ

        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('a' + random.nextInt(26)); // aからzまでのランダムな文字を生成
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString() + ".png";
    }

}
