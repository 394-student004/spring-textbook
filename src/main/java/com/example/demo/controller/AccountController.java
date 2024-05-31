package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

	// ログイン画面表示
	@GetMapping({ "/", "/logout" })
	public String index() {
		return "login";
	}

	// ログイン実行
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {
		return "redirect:/function";
	}

	// 新規会員登録画面表示

	// 新規会員登録情報入力

	// 新規会員登録確認画面表示

	// 新規登録処理

	// 会員情報変更画面表示

	// 会員情報変更内容入力

	// 会員情報変更内容確認画面表示

	// 会員情報変更処理

}
