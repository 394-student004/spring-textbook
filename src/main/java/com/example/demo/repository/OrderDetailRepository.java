package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	//前：List<OrderDetailHistory> findAllOrderDetailHistory();
	//List<OrderDetail> findByAccountIdOrderById(Integer AccountId); //追加した

	//List<OrderDetail> findByQuantityOrderById(Integer id);

	List<OrderDetail> findByAccountIdOrderById(Integer id);

	// 購入キャンセル用
	OrderDetail findByOrderId(Integer orderId);

	//List<OrderDetail> findByQuantityAndAccountId(Integer id, Integer id2);

}
