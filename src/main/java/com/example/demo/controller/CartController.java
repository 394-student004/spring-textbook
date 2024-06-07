package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.model.Cart;
import com.example.demo.repository.ItemRepository;

@Controller
public class CartController {

	@Autowired
	Cart cart;

	@Autowired
	ItemRepository itemRepository;

	// カートの内容を表示
	@GetMapping("/cart")
	public String index() {
		return "cart";
	}

	// 商品をカートに追加
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam("itemId") Integer itemId,
			@RequestParam(name = "quantity", defaultValue = "1") Integer quantity,
			@RequestParam(name = "stock") Integer stock) {

		// 教科書IDで情報を取得
		Item item = itemRepository.findById(itemId).get();
		// 個数をセット
		item.setQuantity(quantity);
		// 在庫をセット
		item.setStock(stock);
		//		itemRepository.save(item);
		// カートに追加
		cart.add(item);
		return "redirect:/cart";
	}

	// 商品をカートから削除
	@PostMapping("/cart/delete")
	public String deleteCart(@RequestParam("itemId") Integer itemId) {
		// カートから削除
		cart.delete(itemId);
		return "redirect:/cart";
	}

}
