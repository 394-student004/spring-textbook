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
	private Integer id;
	@Column(name = "order_id")
	private Integer orderId; //注文ID
	@Column(name = "item_id")
	private Integer itemId; //教科書ID
	private Integer quantity; //数量
	//コンストラクタ

	public OrderDetail() {
	}

	public OrderDetail(Integer orderId, Integer itemId, Integer quantity) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	//アクセッサ
	public Integer getId() {
		return id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
