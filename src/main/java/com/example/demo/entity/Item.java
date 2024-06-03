package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "item")
public class Item {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //教科書ID
	private String name; //教科書名
	private String lecture; //講義名
	private String professor; //教授名
	private int price; //値段
	@Transient
	private int quantity;
	
	//コンストラクタ
	Item(){}
	
	Item(int id, String name, String lecture, String professor, int price){
		this.id = id;
		this.name = name;
		this.lecture = lecture;
		this.professor = professor;
		this.price = price;
	}
	
	//アクセッサ
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLecture() {
		return lecture;
	}

	public String getProfessor() {
		return professor;
	}

	public int getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
