package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Tmp;
import com.example.demo.repository.TmpRepository;
import com.example.demo.service.UpdateDbService;

@Controller
@RequestMapping("/furukari")
public class AdminController {

    @Autowired
    UpdateDbService updateDbService;

    @Autowired
    TmpRepository tmpRepository;

    @GetMapping("/admin")
    public String admin(Model model) {

        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
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

        // Tmp tmp = new Tmp();
        Optional<Tmp> tmp = tmpRepository.findById(1);
        String username = tmp.get().getName();
        // usersテーブルのレコードを承認済みに更新
        // final String loginedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        updateDbService.updateUserVerify(username);
        updateDbService.deleteTable(username);
        // updateDbService.updateUserVerify(loginedUsername);

        return "admin/admin";
    }
}
