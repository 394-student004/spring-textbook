package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class OrderDetailHistory {

	private Integer orderId;
	private Integer itemId;
	private Integer quantity;

	public OrderDetailHistory() {
	}

	public OrderDetailHistory(Integer orderId, Integer itemId, Integer quantity) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
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
