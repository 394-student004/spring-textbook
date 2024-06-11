package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	// 新規登録時、同じメールアドレス登録不可用
	List<Account> findByEmail(String email);

	// ログイン用
	List<Account> findByEmailAndPassword(String email, String password);
}
