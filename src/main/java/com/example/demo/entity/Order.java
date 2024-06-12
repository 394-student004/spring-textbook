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
	private Integer id; // 注文ID
	@Column(name = "account_id")
	private Integer accountId; // 会員ID
	@Column(name = "ordered_on")
	private LocalDate orderedOn; // 注文日
	@Column(name = "point_price")
	private Integer pointPrice; // ポイント使用後の合計額

	//コンストラクタ
	public Order() {
	}

	// 注文情報用
	public Order(Integer accountId, LocalDate orderedOn, Integer pointPrice) {
		this.accountId = accountId;
		this.orderedOn = orderedOn;
		this.pointPrice = pointPrice;
	}

	// 注文履歴表示用
	public Order(Integer accountId, LocalDate orderedOn) {
		this.accountId = accountId;
		this.orderedOn = orderedOn;
	}

	//アクセッサ
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

	public Integer getPointPrice() {
		return pointPrice;
	}

	public void setPointPrice(Integer pointPrice) {
		this.pointPrice = pointPrice;
	}

}
