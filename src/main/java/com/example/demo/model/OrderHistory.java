package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class OrderHistory {
	//フィールド
	private Integer orderId;
	private Integer accountId;
	private LocalDate orderedOn;
	private Integer totalPrice;
	//コンストラクタ
	public OrderHistory() {
	}

	public OrderHistory(Integer orderId, Integer accountId, LocalDate orderedOn, Integer totalPrice) {
		this.orderId = orderId;
		this.accountId = accountId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;

	}

	public OrderHistory(Integer accountId, LocalDate orderedOn, Integer totalPrice) {
		this.accountId = accountId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;

	}
	
	//アクセッサ
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
