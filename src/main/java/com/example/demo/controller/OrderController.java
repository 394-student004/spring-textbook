package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Cart;
import com.example.demo.model.Login;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class OrderController {

	Account account = new Account();

	@Autowired
	Cart cart;

	@Autowired
	Login login;

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
		account = accountRepository.findById(login.getId()).get();
		model.addAttribute("account", account);

		// 注文確認画面に遷移
		return "purchase";
	}

	@GetMapping("/credit")
	public String credit(Model model) {
		// ログインしている顧客IDで顧客テーブルを検索	
		account = accountRepository.findById(login.getId()).get();
		model.addAttribute("account", account);

		// 注文確認画面に遷移
		return "credit";
	}

	// 注文処理
	@PostMapping("/order")
	public String order(
			@RequestParam(name = "card", defaultValue = "") Integer card,
			@RequestParam(name = "date", defaultValue = "") Integer date,
			@RequestParam(name = "code", defaultValue = "") Integer code,
			Model model) {
		// 注文情報をDBに格納する
		Order order = new Order(
				account.getId(),
				LocalDate.now(),
				cart.getTotalPrice());
		orderRepository.save(order);

		// 注文詳細情報をDBに格納する
		List<Item> itemList = cart.getItemList();
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
		model.addAttribute("totalPrice", order.getTotalPrice());
		return "purchaseFin";
	}

	/*
		// 購入履歴を表示
		@GetMapping("/history")
		public String history(Model model) {
			List<OrderHistory> histories = orderRepository.findAllOrderHistory();
			List<OrderDetailHistory> detailHistories = orderDetailRepository.findAllOrderDetailHistory();
	
			model.addAttribute("histories", histories);
			model.addAttribute("detailHistories", detailHistories);
	
			return "history";
		}
	*/
}
