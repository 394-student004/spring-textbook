package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	// 一覧
	List<Item> findAllByOrderById();

	// 教科書名検索
	List<Item> findByNameContainingOrderById(String keyword);

	// 講義名検索
	List<Item> findByLectureContainingOrderById(String lecture);

	// 講師名検索
	List<Item> findByProfessorContainingOrderById(String professor);

	// 教科書名・講義名検索
	List<Item> findByNameContainingAndLectureContainingOrderById(String keyword, String lecture);

	// 講義名・講師名検索
	List<Item> findByLectureContainingAndProfessorContainingOrderById(String lecture, String professor);

	// 教科書名・講師名検索
	List<Item> findByNameContainingAndProfessorContainingOrderById(String keyword, String professor);

	// 教科書名・講義名・講師名検索
	List<Item> findByNameContainingAndLectureContainingAndProfessorContainingOrderById(String keyword, String lecture,
			String professor);

}