package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	// 注文履歴表示用
	List<Order> findByAccountIdOrderByIdDesc(Integer AccountId);

}
