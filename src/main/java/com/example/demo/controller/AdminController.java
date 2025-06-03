package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.UpdateDbService;

@Controller
@RequestMapping("/furukari")
public class AdminController {

    @Autowired
    UpdateDbService updateDbService;

    @GetMapping("/admin")
    public String admin(Model model) {

        String path = "src/main/resources/static/tmp/document.jpg";
        Path p = Paths.get(path);

        if (Files.exists(p)) {
            model.addAttribute("img", 1);
        } else {
            System.out.println("ファイルまたはディレクトリは存在しません");
        }

        return "admin/admin";
    }

    @PostMapping("/admin")
    public String acceptUserVerify(
            Model model,
            RedirectAttributes redirectAttributes) {

        model.addAttribute("accept", 1);
        model.addAttribute("uploaded", 0);
        redirectAttributes.addAttribute("accept", 1);

        // usersテーブルのレコードを承認済みに更新
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        updateDbService.updateUserVerify(username);

        return "redirect:/furukari/item/sell";
    }
}
