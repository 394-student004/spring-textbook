package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Order;
//import com.example.demo.model.OrderHistory;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	// 前：List<OrderHistory> findAllOrderHistory();
	List<Order> findByAccountIdOrderById(Integer AccountId); //追加した

	// 注文キャンセル
	//	Optional<Order> findById(Integer id);

}
