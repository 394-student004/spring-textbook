package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	//　フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 会員ID
	private String name; // 会員名
	private Integer grade; // 学年
	private String department; // 学部
	private String email; // メールアドレス
	private String address; // 住所
	private String password; // パスワード

	//　コンストラクタ
	public Account() {
	}

	// 登録用
	public Account(String name, Integer grade, String department, String email, String address, String password) {
		this.name = name;
		this.grade = grade;
		this.department = department;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	// 更新用
	public Account(Integer id, String name, Integer grade, String department, String email, String address,
			String password) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.department = department;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	// アクセッサ
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
