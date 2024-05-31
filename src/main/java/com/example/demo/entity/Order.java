package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "member_id")
	private int memberId;
	@Column(name = "ordered_on")
	private LocalDate orderedOn;
	@Column(name = "total_price")
	private int totalPrice;
	
	//コンストラクタ
	Order(){}
	
	Order(int memberId, LocalDate orderedOn, int totalPrice){
		this.memberId = memberId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;
	}
	
	//アクセッサ
	public int getId() {
		return id;
	}

	public int getMemberId() {
		return memberId;
	}

	public LocalDate getOrderedOn() {
		return orderedOn;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	
}
