package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.model.Login;
import com.example.demo.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	Account account = new Account();

	@Autowired
	Login login;

	@Autowired
	AccountRepository accountRepository;

	// ログイン画面表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報を全てクリアする
		session.invalidate();
		// エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}
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
		//account.setId(account.getId());
		//account.setName(account.getName());

		login.setName(account.getName());
		login.setId(account.getId());

		// 「/function」機能一覧画面へのリダイレクト
		return "redirect:/function";
	}

	// 新規会員登録画面表示
	@GetMapping("/account")
	public String create() {
		return "join";
	}

	// 新規会員登録情報入力
	@PostMapping("/account/add")
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

		// エラー発生時はお問い合わせフォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute("grade", grade);
			model.addAttribute("department", department);
			model.addAttribute("email", email);
			model.addAttribute("address", address);
			return "join";
		}
		model.addAttribute("name", name);
		model.addAttribute("grade", grade);
		model.addAttribute("department", department);
		model.addAttribute("email", email);
		model.addAttribute("address", address);
		account = new Account(name, grade, department, email, address, password);
		accountRepository.save(account);
		return "loginConfirm";
	}

	// 会員情報変更画面表示
	@GetMapping("/account/edit")
	public String edit(Model model) {
		account = accountRepository.findById(login.getId()).get();
		model.addAttribute("account", account);
		return "accountEdit";
	}

	// 会員情報変更内容入力
	@PostMapping("/account/edit")
	public String update(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "grade", defaultValue = "") Integer grade,
			@RequestParam(name = "department", defaultValue = "") String department,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		if (name.length() == 0 || grade == null || department.length() == 0 || email.length() == 0
				|| address.length() == 0
				|| password.length() == 0) {
			model.addAttribute("error", "全ての項目を入力してください");
			return "accountEdit";
		} else {
			Account editAccount = accountRepository.findById(login.getId()).get();
			accountRepository.delete(editAccount);

			model.addAttribute("name", name);
			model.addAttribute("grade", grade);
			model.addAttribute("department", department);
			model.addAttribute("email", email);
			model.addAttribute("address", address);

			account = new Account(name, grade, department, email, address, password);
			account.setId(login.getId());
			accountRepository.save(account);
			return "accountConfirm";
		}
	}
}
