package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	Account account = new Account();

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

		// 教科書一覧情報の取得
		List<Item> itemList = null;
		if (keyword.length() > 0 && lecture.length() > 0 && professor.length() > 0) {
			// 商品名かつ講義名かつ講師名検索
			itemList = itemRepository.findByNameContainingAndLectureContainingAndProfessorContaining(keyword, lecture,
					professor);
		} else if (keyword.length() > 0 && lecture.length() > 0) {
			itemList = itemRepository.findByNameContainingAndLectureContaining(keyword, lecture);
		} else if (keyword.length() > 0 && professor.length() > 0) {
			itemList = itemRepository.findByNameContainingAndLectureContaining(keyword, professor);
		} else if (lecture.length() > 0 && professor.length() > 0) {
			itemList = itemRepository.findByLectureContainingAndProfessorContaining(lecture, professor);
		} else if (keyword.length() > 0) {
			// itemsテーブルを教科書名で部分一致検索
			itemList = itemRepository.findByNameContaining(keyword);
		} else if (lecture.length() > 0) {
			itemList = itemRepository.findByLectureContaining(lecture);
		} else if (professor.length() > 0) {
			itemList = itemRepository.findByProfessorContaining(professor);
		} else {
			// 教科書一覧
			itemList = itemRepository.findAll();
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("lecture", lecture);
		model.addAttribute("professor", professor);
		model.addAttribute("items", itemList);
		return "textbook";
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
