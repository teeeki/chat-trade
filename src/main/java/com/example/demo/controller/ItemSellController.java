package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Item;
import com.example.demo.entity.Tmp;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.TmpRepository;
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
    TmpRepository tmpRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 出品フォームを表示
     * @param param
     * @return
     */
    @GetMapping("/item/sell")
    public String itemSellForm(Model model) {

        // ユーザ情報を取り出す
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        User user = userRepository.findByUsername(username).get();

        // is_verifyがtrueなら出品フォームへ
        // is_verifyがfalseなら本人確認フォームへ
        if (user.getIsVerify() == true) {
            return "sell/itemListForm.html";
        } else {
            // userテーブルを確認中に更新
            Tmp tmp = new Tmp();
            tmp.setName(username);
            tmpRepository.save(tmp);

            return "user_verify/userVerifyForm.html";
        }

    }

    /**
     * 出品処理を担当
     * テンプレートフォーム（itemListForm.html）から受け取った出品情報をitemテーブルに格納
     * @param name：商品名
     * @param price：価格
     * @param abst：概要
     * @param description：詳細
     * @param img：画像
     * @return：確認画面
     */
    @PostMapping("/item/sell")
    public String itemSell(
            @RequestParam("name") String name,
            @RequestParam("price") Integer price,
            @RequestParam("abst") String abst,
            @RequestParam("description") String description,
            @RequestParam("img") MultipartFile img,
            Model model) {

        // ログイン中のユーザIDを取得
        final String loginedName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", loginedName);
        loginedUser = userRepository.findByUsername(loginedName).get();
        Integer userId = loginedUser.getId();

        String imgPath = null;
        try {
            // ファイル名
            String imgName = img.getOriginalFilename();
            // 保存先パス
            imgPath = "/item_img/" + imgName;
            // 画像ファイルをバイナリデータとして取得
            byte[] content = img.getBytes();
            // 保存
            Files.write(Paths.get("src/main/resources/static/item_img/" + imgName), content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Item item = new Item(
                userId,
                name,
                price,
                abst,
                description,
                imgPath);

        // セッションに保存（確認画面で使用するため）
        session.setAttribute("tempItem", item);

        model.addAttribute("item", item);
        // itemRepository.save(item);

        return "sell/itemSellConfirm";
    }

    @PostMapping("/item/sell/submit")
    public String itemSellSubmit(Model model) {

        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        System.out.println("出品確定");
        System.out.println("出品確定");
        System.out.println("出品確定");

        // セッションからアイテムを取得
        Item item = (Item) session.getAttribute("tempItem");

        itemRepository.save(item);
        return "sell/itemSellSubmit";
    }

}
