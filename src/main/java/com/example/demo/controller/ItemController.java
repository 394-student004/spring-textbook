package com.example.demo.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	Account account;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	// 機能一覧画面表示

	// 教科書一覧画面表示、検索
	@GetMapping("/items")
	public String index(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "maxPrice", defaultValue = "") Integer maxPrice,
			Model model) {

		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// 商品一覧情報の取得
		List<Item> itemList = null;
		if (keyword.length() > 0 && maxPrice != null) {
			// 商品名かつ価格検索
			itemList = itemRepository.findByNameContainingAndPriceLessThanEqual(keyword, maxPrice);
		} else if (keyword.length() > 0) {
			// itemsテーブルを商品名で部分一致検索
			itemList = itemRepository.findByNameContaining(keyword);
		} else {
			// 全商品一覧
			itemList = itemRepository.findAll();
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("items", itemList);
		return "items";
	}

	@GetMapping("/items/{id}")
	public String show(
			@PathVariable("id") Integer id,
			Model model) {

		// 主キー検索
		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);

		return "itemDetail";
	}

}
