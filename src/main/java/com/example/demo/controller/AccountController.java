package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Item;

@Controller
public class AccountController {

	@Autowired
	Account account;

	@Autowired
	AccountRepository accountRepository;

	// ログイン画面表示
	@GetMapping({ "/", "/logout" })
	public String index() {
		return "login";
	}

	// ログイン実行
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {
		// 名前が空の場合にエラーとする
		if (email.length() == 0 || password.length() == 0) {
			model.addAttribute("message", "入力してください");
			return "login";
		}
		List<Account> accountList = accountRepository.findByEmailAndPassword(email, password);
		if (accountList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
		Account account = accountList.get(0);

		// セッション管理されたアカウント情報にIDと名前をセット
		account.setId();
		account.setName();

		// 「/function」機能一覧画面へのリダイレクト
		return "redirect:/function";
	}

	// 新規会員登録画面表示
	@GetMapping("/account")
	public String create() {
		return "accountForm";
	}

	// 新規会員登録情報入力
	@PostMapping("/account")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "grade", defaultValue = "") Integer grade,
			@RequestParam(name = "department", defaultValue = "") String department,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if (name.length() == 0) {
			errorList.add("名前は必須です");
		}
		if (grade == null) {
			errorList.add("学年は必須です");
		}
		if (department.length() == 0) {
			errorList.add("学部は必須です");
		}
		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		}
		if (address.length() == 0) {
			errorList.add("住所は必須です");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}

		// メールアドレス存在チェック
		List<Account> accountList = accountRepository.findByEmail(email);
		if (accountList.size() > 0) {
			// 登録済みのメールアドレスが存在した場合
			errorList.add("登録済みのメールアドレスです");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}

		// エラー発生時はお問い合わせフォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute("grade", grade);
			model.addAttribute("department", department);
			model.addAttribute("email", email);
			model.addAttribute("address", address);
			model.addAttribute("password", password);
			return "accountForm";
		}
		Account account = new Account(name, grade, department, email, address, password);
		accountRepository.save(account);
		return "";
	}

	// 新規会員登録内容画面表示
	/*	@GetMapping("/")
	  	public String aaa() {
	 	return "redirect:/";
	 	}
	*/
	// 会員情報変更画面表示
	@GetMapping("/account/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {

		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);
		return "";
	}

	// 会員情報変更内容入力
	@PostMapping("/items/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			Model model) {

		Item item = new Item(id, categoryId, name, price);
		itemRepository.save(item);
		return "";
	}

	// 会員情報変更内容画面表示
	/*	@GetMapping("/") {
	  return "redirect:/";
	  }
	*/

}
