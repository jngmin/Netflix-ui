package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@GetMapping("/")
		public String home(HttpSession session, Model model) {
        String userType = (String) session.getAttribute("type");
        model.addAttribute("userType", userType);
		return "common/home/home";
	}
}
