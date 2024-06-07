package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "item")
public class Item {
	//フィールド
	@Id
	//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //教科書ID
	private String name; //教科書名
	private String lecture; //講義名
	private String professor; //教授名
	private Integer price; //値段
	private Integer stock; // 数量

	@Transient
	private Integer quantity;

	//コンストラクタ
	public Item() {
	}

	public Item(Integer id, String name, String lecture, String professor, Integer price) {
		this.id = id;
		this.name = name;
		this.lecture = lecture;
		this.professor = professor;
		this.price = price;
	}

	//アクセッサ
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

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
