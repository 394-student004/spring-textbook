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
import com.example.demo.model.OrderHistory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ItemRepository;
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
	OrderHistory orderHistory;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	// 注文内容確認とお客様情報画面を表示
	@GetMapping("/order")
	public String index(Model model) {
		// 商品が選択されていない場合は購入に進めない
		if (cart.getTotalPrice() == 0) {
			model.addAttribute("error", "商品が選択されていません");
			return "cart";
		}
		// ログインしている会員の情報を取得	
		account = accountRepository.findById(login.getId()).get();
		model.addAttribute("account", account);
		return "purchase";
	}

	// 注文処理
	@PostMapping("/order")
	public String order(
			@RequestParam("itemId") Integer itemId,
			Model model) {
		// 注文情報をDBに格納する
		Order order = new Order(
				login.getId(),
				LocalDate.now(),
				cart.getTotalPrice());
		orderRepository.save(order);

		// 注文詳細情報をDBに格納する
		List<Item> itemList = cart.getItemList();
		List<OrderDetail> orderDetails = new ArrayList<>();
		List<Item> editStock = new ArrayList<>();
		for (Item item : itemList) {
			orderDetails.add(
					new OrderDetail(
							login.getId(),
							order.getId(),
							item.getId(),
							item.getName(),
							item.getQuantity(),
							item.getStock()));
			// DBの在庫数更新
			editStock.add(item);
		}
		itemRepository.saveAll(editStock);
		orderDetailRepository.saveAll(orderDetails);
		// ポイント
		int point = cart.getPoint();
		// 画面返却用注文番号を設定する
		model.addAttribute("orderNumber", order.getId());
		model.addAttribute("totalPrice", order.getTotalPrice());
		model.addAttribute("point", point);
		// カートの情報をクリア
		cart.clear();
		return "purchaseFin";
	}

	// 注文内容確認とお客様情報画面を表示（クレジット）
	@GetMapping("/credit")
	public String credit(Model model) {
		// 商品が選択されていない場合は購入に進めない
		if (cart.getTotalPrice() == 0) {
			model.addAttribute("error", "商品が選択されていません");
			return "cart";
		}
		// ログインしている会員の情報を取得	
		account = accountRepository.findById(login.getId()).get();
		model.addAttribute("account", account);

		return "credit";
	}

	// 注文処理（クレジット）
	@PostMapping("/order/credit")
	public String credit(
			@RequestParam(name = "card", defaultValue = "") String card,
			@RequestParam(name = "date", defaultValue = "") Integer date,
			@RequestParam(name = "code", defaultValue = "") Integer code,
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// エラーチェック
		// 空欄の場合はエラー
		if (card.isEmpty() || code == null) {
			model.addAttribute("error", "入力してください");
			account = accountRepository.findById(login.getId()).get();
			model.addAttribute("account", account);
			return "credit";
		}
		// カード番号が16桁でない場合はエラー
		if (card.length() != 19) {
			model.addAttribute("error", "カード番号は16桁で入力してください");
			account = accountRepository.findById(login.getId()).get();
			model.addAttribute("account", account);
			return "credit";
		}
		// セキュリティコードが3桁でない場合はエラー
		if (code.toString().length() != 3) {
			model.addAttribute("error", "セキュリティコードは3桁で入力してください");
			account = accountRepository.findById(login.getId()).get();
			model.addAttribute("account", account);
			return "credit";
		}
		// 注文情報をDBに格納する
		Order order = new Order(
				login.getId(),
				LocalDate.now(),
				cart.getTotalPrice());
		orderRepository.save(order);
		// 注文詳細情報をDBに格納する
		List<Item> itemList = cart.getItemList();
		List<OrderDetail> orderDetails = new ArrayList<>();
		List<Item> editStock = new ArrayList<>();
		for (Item item : itemList) {
			orderDetails.add(
					new OrderDetail(
							login.getId(),
							order.getId(),
							item.getId(),
							item.getName(),
							item.getQuantity(),
							item.getStock()));
			// DB在庫数の変更処理
			editStock.add(item);
		}
		itemRepository.saveAll(editStock);
		orderDetailRepository.saveAll(orderDetails);
		// ポイント
		int point = cart.getPoint();
		// 画面返却用注文番号を設定する
		model.addAttribute("orderNumber", order.getId());
		model.addAttribute("totalPrice", order.getTotalPrice());
		model.addAttribute("point", point);
		// カートの情報をクリア
		cart.clear();
		return "purchaseFin";
	}

	// 購入履歴を表示
	@GetMapping("/history")
	public String history(Model model) {
		// 注文履歴表示
		List<Order> histories = orderRepository.findByAccountIdOrderById(login.getId());
		List<OrderDetail> detailHistories = orderDetailRepository.findByAccountIdOrderById(login.getId());
		model.addAttribute("histories", histories);
		model.addAttribute("detailHistories", detailHistories);
		return "history";
	}

	// 購入をキャンセルする（削除）
	@PostMapping("/history/delete")
	public String delete(
			@RequestParam("historyId") Integer historyId,
			Model model) {
		// 在庫復活
		List<Item> itemList = itemRepository.findAll();
		List<OrderDetail> orderdetailList = orderDetailRepository.findByOrderId(historyId);
		List<Item> addStock = new ArrayList<>();
		for (OrderDetail item : orderdetailList) {
			for (Item items : itemList) {
				if (item.getItemId() == items.getId()) {
					items.setStock(item.getQuantity() + items.getStock());
					addStock.add(items);
				}
			}
		}
		itemRepository.saveAll(addStock);
		// 注文履歴削除
		List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(historyId);
		orderDetailRepository.deleteAll(orderDetails);
		orderRepository.deleteById(historyId);
		return "historyConfirm";
	}
}
