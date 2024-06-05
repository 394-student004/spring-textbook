package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderDetail;
import com.example.demo.model.OrderDetailHistory;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	//前：List<OrderDetailHistory> findAllOrderDetailHistory();
	List<OrderDetailHistory> findByAccountIdOrderById(Integer AccountId); //追加した
}
