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
	private Integer id;
	@Column(name = "account_id")
	private Integer accountId; //会員ID
	@Column(name = "ordered_on")
	private LocalDate orderedOn; //注文日
	@Column(name = "total_price")
	private Integer totalPrice; //合計額

	private Integer card;
	private Integer date;

	//コンストラクタ
	public Order() {
		super();
	}

	//アクセッサ
	public Order(Integer accountId, LocalDate orderedOn, Integer totalPrice) {
		super();
		this.accountId = accountId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;
	}

	public Order(Integer accountId, LocalDate orderedOn) {
		super();
		this.accountId = accountId;
		this.orderedOn = orderedOn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public LocalDate getOrderedOn() {
		return orderedOn;
	}

	public void setOrderedOn(LocalDate orderedOn) {
		this.orderedOn = orderedOn;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
