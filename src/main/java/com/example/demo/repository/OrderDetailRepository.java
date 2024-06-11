package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	// 注文履歴表示用
	List<OrderDetail> findByAccountIdOrderById(Integer id);

	// 注文キャンセル用
	List<OrderDetail> findByOrderId(Integer id);

}
