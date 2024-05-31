package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String lecture;
	private String professor;
	private int price;
	
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
	
	
	
}
