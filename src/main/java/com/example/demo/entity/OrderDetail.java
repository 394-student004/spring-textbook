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

	// フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 注文明細ID
	@Column(name = "account_id")
	private Integer accountId; // 会員ID
	@Column(name = "order_id")
	private Integer orderId; // 注文ID
	@Column(name = "item_id")
	private Integer itemId; // 教科書ID
	@Column(name = "item_name")
	private String itemName; // 教科書名
	private Integer quantity; // 数量
	@Column(name = "item_stock")
	private Integer itemStock; // 在庫
	@Column(name = "account_point")
	private Integer accountPoint; // ポイント

	// コンストラクタ
	public OrderDetail() {
	}

	// 注文の注文詳細情報用
	public OrderDetail(Integer accountId, Integer orderId, Integer itemId, String itemName, Integer quantity,
			Integer itemStock) {
		this.accountId = accountId;
		this.orderId = orderId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemStock = itemStock;
	}

	// 注文の注文詳細情報用
	public OrderDetail(Integer accountId, Integer orderId, Integer itemId, String itemName, Integer quantity,
			Integer itemStock, Integer accountPoint) {
		this.accountId = accountId;
		this.orderId = orderId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemStock = itemStock;
		this.accountPoint = accountPoint;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getItemStock() {
		return itemStock;
	}

	public void setItemStock(Integer itemStock) {
		this.itemStock = itemStock;
	}

	public Integer getAccountPoint() {
		return accountPoint;
	}

	public void setAccountPoint(Integer accountPoint) {
		this.accountPoint = accountPoint;
	}

}
