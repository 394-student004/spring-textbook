package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "order_id")
	private int orderId; //注文ID
	@Column(name = "item_id")
	private int itemId; //教科書ID
	private int quantity; //数量
	//コンストラクタ

	public OrderDetail() {
	}

	public OrderDetail(int orderId, int itemId, int quantity) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	//アクセッサ
	public int getId() {
		return id;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

}
