package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Item;
import com.example.demo.model.Cart;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	Account account = new Account();

	@Autowired
	Cart cart;

	@Autowired
	ItemRepository itemRepository;

	// 機能一覧画面表示
	@GetMapping("/function")
	public String list() {
		return "function";
	}

	// 教科書一覧画面表示、検索
	@GetMapping("/items")
	public String index(
			@RequestParam(name = "id", defaultValue = "") Integer id,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "lecture", defaultValue = "") String lecture,
			@RequestParam(name = "professor", defaultValue = "") String professor,
			Model model) {
		// 教科書一覧、検索結果表示
		List<Item> itemList = null;
		List<Item> itemListBrows = new ArrayList<>();
		// 検索結果のリストのサイズを取得
		int itemCount = 0;
		// 教科書一覧
		if (keyword.length() <= 0 && lecture.length() <= 0 && professor.length() <= 0) {
			itemList = itemRepository.findAllByOrderById();
		}
		// 教科書名で部分一致検索
		if (keyword.length() > 0) {
			itemList = itemRepository.findByNameContainingOrderById(keyword);
		}
		// 講義名で部分一致検索
		if (lecture.length() > 0) {
			itemList = itemRepository.findByLectureContainingOrderById(lecture);
		}
		// 講師名で部分一致検索
		if (professor.length() > 0) {
			itemList = itemRepository.findByProfessorContainingOrderById(professor);
		}
		// 教科書名と講義名で部分一致検索
		if (keyword.length() > 0 && lecture.length() > 0) {
			itemList = itemRepository.findByNameContainingAndLectureContainingOrderById(keyword, lecture);
		}
		// 教科書名と講師名で部分一致検索
		if (keyword.length() > 0 && professor.length() > 0) {
			itemList = itemRepository.findByNameContainingAndProfessorContainingOrderById(keyword, professor);
		}
		// 講義名と講師名で部分一致検索
		if (lecture.length() > 0 && professor.length() > 0) {
			itemList = itemRepository.findByLectureContainingAndProfessorContainingOrderById(lecture, professor);
		}
		// 教科書名と講義名と講師名で部分一致検索
		if (keyword.length() > 0 && lecture.length() > 0 && professor.length() > 0) {
			itemList = itemRepository.findByNameContainingAndLectureContainingAndProfessorContainingOrderById(
					keyword,
					lecture,
					professor);
		}
		// 検索結果の件数を取得
		if (itemList != null) {
			itemCount = itemList.size();
		}
		// 表示する在庫の更新
		for (Item item : itemList) {
			for (Item items : cart.getItemList()) {
				if (items.getStock() > 0) {
					if (item.getId() == items.getId()) {
						item.setStock(items.getStock());
					}
				} else {
					if (item.getId() == items.getId()) {
						item.setStock(0);
					}
				}
			}
			itemListBrows.add(item);
		}
		// 入力した値の保持用
		model.addAttribute("keyword", keyword);
		model.addAttribute("lecture", lecture);
		model.addAttribute("professor", professor);
		model.addAttribute("itemListBrows", itemListBrows);
		model.addAttribute("itemCount", itemCount);
		return "textbook";
	}

}
