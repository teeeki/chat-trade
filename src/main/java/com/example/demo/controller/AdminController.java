package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/furukari")
public class AdminController {

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

        System.out.println("承認");
        model.addAttribute("accept", 1);
        model.addAttribute("uploaded", 0);
        redirectAttributes.addAttribute("accept", 1);

        return "redirect:/furukari/item/sell";
    }
}
