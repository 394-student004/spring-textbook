package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Login {

	// フィールド
	private String name; // 会員名
	private String email; // メールアドレス
	private Integer id; // 会員ID

	// コンストラクタ
	public Login() {
	}

	// ログインしているユーザーの情報取得用
	public Login(String name, String email, Integer id) {
		this.name = name;
		this.email = email;
		this.id = id;
	}

	// アクセッサ
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}