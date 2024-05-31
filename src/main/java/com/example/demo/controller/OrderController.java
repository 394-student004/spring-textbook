package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Account;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Cart;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class OrderController {
	@Autowired
	Account account;

	@Autowired
	Cart cart;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	// 注文内容確認とお客様情報画面を表示
	@GetMapping("/order")
	public String index(Model model) {

		// ログインしている顧客IDで顧客テーブルを検索		
		Account account = accountRepository.findById().get();
		model.addAttribute("account", account);

		// 注文確認画面に遷移
		return "orderConfirm";
	}

	// 注文処理
	@PostMapping("/order")
	public String order(Model model) {
		// 注文情報をDBに格納する
		Order order = new Order(
				account.getId(),
				LocalDate.now(),
				cart.getTotalPrice());
		orderRepository.save(order);

		// 注文詳細情報をDBに格納する
		List<Item> itemList = cart.getItems();
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (Item item : itemList) {
			orderDetails.add(
					new OrderDetail(
							order.getId(),
							item.getId(),
							item.getQuantity()));
		}
		orderDetailRepository.saveAll(orderDetails);

		// セッションスコープのカート情報をクリアする
		cart.clear();

		// 画面返却用注文番号を設定する
		model.addAttribute("orderNumber", order.getId());
		return "ordered";
	}

	// 購入履歴を表示

}
