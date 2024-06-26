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
	public String index() {
		// セッション情報を全てクリアする
		session.invalidate();
		return "login";
	}

	// ログイン実行
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {
		// 名前が空の場合はエラー
		if (email.length() == 0 || password.length() == 0) {
			model.addAttribute("message", "入力してください");
			return "login";
		}
		// 入力した値の照合
		List<Account> accountList = accountRepository.findByEmailAndPassword(email, password);
		if (accountList.size() == 0) {
			// DBと一致しなかった場合
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
		// ログイン成功処理
		Account account = accountList.get(0);
		login.setName(account.getName());
		login.setId(account.getId());
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
		// 既存のメールアドレス存在チェック
		List<Account> accountList = accountRepository.findByEmail(email);
		if (accountList.size() > 0) {
			// 登録済みのメールアドレスが存在した場合
			errorList.add("登録済みのメールアドレスです");
		}
		// エラー時は新規登録画面に戻る
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute("grade", grade);
			model.addAttribute("department", department);
			model.addAttribute("email", email);
			model.addAttribute("address", address);
			return "join";
		}
		// 新規登録成功処理
		account = new Account(name, grade, department, email, address, password, 0);
		accountRepository.save(account);
		// 確認画面に表示する用
		model.addAttribute("id", account.getId());
		model.addAttribute("name", name);
		model.addAttribute("grade", grade);
		model.addAttribute("department", department);
		model.addAttribute("email", email);
		model.addAttribute("address", address);
		model.addAttribute("point", account.getPoint());
		return "loginConfirm";
	}

	//会員情報確認画面用
	@GetMapping("/account/form")
	public String form(Model model) {
		// ログインしている会員の情報を取得
		account = accountRepository.findById(login.getId()).get();
		// 確認画面に表示する用
		model.addAttribute("id", account.getId());
		model.addAttribute("name", account.getName());
		model.addAttribute("grade", account.getGrade());
		model.addAttribute("department", account.getDepartment());
		model.addAttribute("email", account.getEmail());
		model.addAttribute("address", account.getAddress());
		model.addAttribute("point", account.getPoint());
		return "accountForm";
	}

	// 会員情報変更画面表示
	@GetMapping("/account/edit")
	public String edit(Model model) {
		// ログインしている会員の情報を取得
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
			@RequestParam(name = "point") Integer point,
			Model model) {
		// エラーチェック
		// ログインしているユーザーの情報を取得
		Account editAccount = accountRepository.findById(login.getId()).get();
		// 既存のメールアドレス存在チェック
		List<Account> accountList = accountRepository.findByEmail(email);
		for (Account account : accountList) {
			if (accountList.size() > 0) {
				if (account.getEmail() != editAccount.getEmail()) {
					// 登録済みのメールアドレスが存在した場合
					model.addAttribute("message", "登録済みのメールアドレスです");
					// ログインしている会員の情報を取得して変更画面に戻る
					account = accountRepository.findById(login.getId()).get();
					model.addAttribute("account", account);
					return "accountEdit";
				}
			}
		}
		// 空の場合はエラー
		if (name.length() == 0 || grade == null || department.length() == 0 || email.length() == 0
				|| address.length() == 0 || password.length() == 0) {
			model.addAttribute("error", "全ての項目を入力してください");
			// ログインしている会員の情報を取得して変更画面に戻る
			account = accountRepository.findById(login.getId()).get();
			model.addAttribute("account", account);
			return "accountEdit";
		} else {
			// 確認画面に表示する用
			model.addAttribute("name", name);
			model.addAttribute("grade", grade);
			model.addAttribute("department", department);
			model.addAttribute("email", email);
			model.addAttribute("address", address);
			// 変更内容を登録
			account = new Account(login.getId(), name, grade, department, email, address, password, point);
			accountRepository.save(account);
			return "accountConfirm";
		}
	}

	// ポイント詳細画面表示
	@GetMapping("/point")
	public String point(Model model) {
		// ログインしている会員の情報を取得
		account = accountRepository.findById(login.getId()).get();
		model.addAttribute("account", account);
		return "point";
	}

}
