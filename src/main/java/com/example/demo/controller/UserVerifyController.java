package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 出品するユーザの本人確認書類の審査を担当
 */
@Controller
@RequestMapping("/furukari")
public class UserVerifyController {

    @GetMapping("/user-verify")
    public String userVerifyForm() {
        return "user_verify/userVerifyForm";
    }

    @PostMapping("/user-verify")
    public String userVerifyUpload(
            @RequestParam("img") MultipartFile img,
            Model model) {

        try {
            // ファイル名
            String imgName = img.getOriginalFilename();
            // 画像ファイルをバイナリデータとして取得
            byte[] content = img.getBytes();
            // 保存
            Files.write(Paths.get("src/main/resources/static/tmp/" + imgName), content);
            model.addAttribute("uploaded", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "user_verify/userVerifyForm";
    }

}
