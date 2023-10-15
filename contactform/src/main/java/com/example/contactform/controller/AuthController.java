package com.example.contactform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.contactform.entity.User;
import com.example.contactform.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
     @GetMapping("/login")
     public String login() {        
         return "auth/login";
     }
     @PostMapping("/auth/login")
     public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
         // データベースからユーザー情報を取得
         User user = userRepository.findByEmail(email);

         if (user != null && user.getPassword().equals(password)) {
             // 認証に成功した場合、ユーザー情報をセッションに格納
             session.setAttribute("userEmail", email);
             return "redirect:/form/list"; // ログイン成功後のリダイレクト先
         } else {
             // 認証に失敗した場合、エラーメッセージを表示
             model.addAttribute("error", true);
             return "auth/login";
         }
     }
}
