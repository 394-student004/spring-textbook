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
	@Column(name = "account_id")
	private Integer accountId; // 会員ID　追加した
	@Column(name = "order_id")
	private Integer orderId; // 注文ID
	@Column(name = "item_id")
	private Integer itemId; // 教科書ID
	@Column(name = "item_name")
	private String itemName; // 教科書名
	private Integer quantity; // 数量
	@Column(name = "item_stock")
	private Integer itemStock; // 在庫

	//コンストラクタ
	public OrderDetail() {
	}

	public OrderDetail(Integer accountId, Integer orderId, Integer itemId, String itemName, Integer quantity) {
		this.accountId = accountId;
		this.orderId = orderId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
	}

	public OrderDetail(Integer accountId, Integer orderId, String itemName, Integer quantity) {
		this.accountId = accountId;
		this.orderId = orderId;
		this.itemName = itemName;
		this.quantity = quantity;
	}

	public OrderDetail(Integer accountId, Integer orderId, Integer itemId, Integer quantity) {
		this.accountId = accountId;
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public OrderDetail(Integer orderId, String itemName, Integer quantity) {
		this.orderId = orderId;
		this.itemName = itemName;
		this.quantity = quantity;
	}

	// ストック追加
	public OrderDetail(Integer accountId, Integer orderId, Integer itemId, String itemName, Integer quantity,
			Integer itemStock) {
		this.accountId = accountId;
		this.orderId = orderId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemStock = itemStock;
	}

	//アクセッサ
	public Integer getId() {
		return id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;

	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getItemStock() {
		return itemStock;
	}

	public void setItemStock(Integer itemStock) {
		this.itemStock = itemStock;
	}

}
