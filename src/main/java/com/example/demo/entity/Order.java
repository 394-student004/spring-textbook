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
	@Column(name = "account_id")
	private int accountId; //会員ID
	@Column(name = "ordered_on")
	private LocalDate orderedOn; //注文日
	@Column(name = "total_price")
	private int totalPrice; //合計額

	//コンストラクタ
	public Order() {
		super();
	}

	public Order(int accountId, LocalDate orderedOn, int totalPrice) {
		super();
		this.accountId = accountId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;
	}

	//アクセッサ
	public int getId() {
		return id;
	}

	public int getAccountId() {
		return accountId;
	}

	public LocalDate getOrderedOn() {
		return orderedOn;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

}
