package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

		// 教科書一覧、検索結果表示
		List<Item> itemList = null;
		// 教科書一覧
		if (keyword.length() <= 0 && lecture.length() <= 0 && professor.length() <= 0) {
			itemList = itemRepository.findAllByOrderById();
			/*									List<Item> itemListBrows = new ArrayList<>();
									for (Item item : itemList) {
										itemListBrows.add(item);
										model.addAttribute("itemListBrows", itemListBrows);
									}
			*/ }
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
			itemList = itemRepository.findByNameContainingAndLectureContainingAndProfessorContainingOrderById(keyword,
					lecture,
					professor);
		}
		// 入力した値の保持用
		model.addAttribute("keyword", keyword);
		model.addAttribute("lecture", lecture);
		model.addAttribute("professor", professor);
		model.addAttribute("items", itemList);
		return "textbook";
	}

}
