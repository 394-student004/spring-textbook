package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	//前：List<OrderDetailHistory> findAllOrderDetailHistory();
	List<OrderDetail> findByAccountIdOrderById(Integer AccountId); //追加した
}
