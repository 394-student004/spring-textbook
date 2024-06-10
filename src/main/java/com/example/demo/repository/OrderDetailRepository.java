package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	// 購入履歴表示用
	List<OrderDetail> findByAccountIdOrderById(Integer id);

	// 購入キャンセル用
	List<OrderDetail> findByOrderId(Integer id);

}
