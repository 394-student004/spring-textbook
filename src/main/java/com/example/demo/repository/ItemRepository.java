package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findAllByOrderById();

	List<Item> findByNameContainingOrderById(String keyword);

	List<Item> findByLectureContainingOrderById(String lecture);

	List<Item> findByProfessorContainingOrderById(String professor);

	List<Item> findByNameContainingAndLectureContainingOrderById(String keyword, String lecture);

	List<Item> findByLectureContainingAndProfessorContainingOrderById(String lecture, String professor);

	List<Item> findByNameContainingAndProfessorContainingOrderById(String keyword, String professor);

	List<Item> findByNameContainingAndLectureContainingAndProfessorContainingOrderById(String keyword, String lecture,
			String professor);

}