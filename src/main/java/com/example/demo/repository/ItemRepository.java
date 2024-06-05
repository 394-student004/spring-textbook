package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByNameContaining(String keyword);

	List<Item> findByLectureContaining(String lecture);

	List<Item> findByProfessorContaining(String professor);

	List<Item> findByNameContainingAndLectureContaining(String keyword, String lecture);

	List<Item> findByLectureContainingAndProfessorContaining(String lecture, String professor);

	List<Item> findByNameContainingAndProfessorContaining(String keyword, String professor);

	List<Item> findByNameContainingAndLectureContainingAndProfessorContaining(String keyword, String lecture,
			String professor);

}