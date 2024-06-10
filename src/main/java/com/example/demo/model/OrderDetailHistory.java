package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class OrderDetailHistory {
	
	//フィールド
	private Integer orderDetailId; //追加した
	private Integer orderId; //注文ID
	private Integer itemId; //教科書ID
	private Integer quantity; //数量
	
	//コンストラクタ
	public OrderDetailHistory() {
	}

	public OrderDetailHistory(Integer orderDetailId, Integer orderId, Integer itemId, Integer quantity) {
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}
	
	//アクセッサ
	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
