package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Account;
import com.example.demo.entity.Item;
import com.example.demo.repository.AccountRepository;

@Component
@SessionScope
public class Cart {

	@Autowired
	Login login;

	Account account = new Account();

	@Autowired
	AccountRepository accountRepository;

	// フィールド
	private List<Item> itemList = new ArrayList<>(); // カートの中身リスト

	// アクセッサ
	public List<Item> getItemList() {
		return itemList;
	}

	//　カートの中身追加
	public void add(Item newItem) {
		Item existsItem = null;
		// 現在のカートから同じ教科書を探す
		for (Item item : itemList) {
			if (item.getId() == newItem.getId()) {
				existsItem = item;
				break;
			}
		}
		// カート内に同じ教科書がなければ追加
		// 存在した場合は個数の追加
		// カートに教科書が追加されると在庫を減らす
		if (existsItem == null) {
			itemList.add(newItem);
			newItem.setStock(newItem.getStock() - 1);
		} else {
			existsItem.setQuantity(existsItem.getQuantity() + newItem.getQuantity());
			existsItem.setStock(existsItem.getStock() - 1);
		}
	}

	// 合計金額取得用
	public Integer getTotalPrice() {
		// 合計金額計算
		Integer total = 0;
		for (Item item : itemList) {
			total += item.getPrice() * item.getQuantity();
		}
		// 送料追加計算
		// 合計金額が5000円以上で送料無料
		if (total == 0) {
			return total;
		} else if (total < 5000) {
			Integer fee = 550;
			total += fee;
		}
		return total;
	}

	// 合計金額から50円につき1ポイントを付与
	public Integer getPoint() {
		Integer point = getTotalPrice() / 50;
		return point;
	}

	// 送料表示用
	public String message() {
		if (getTotalPrice() < 5000) {
			if (getTotalPrice() == 0) {
				String message = "";
				return message;
			}
			String message = "（送料込み）";
			return message;
		}
		String message = "(送料無料)";
		return message;
	}

	// 現在の所持ポイント表示用
	public String message2() {
		List<Account> accountList = accountRepository.findAll();
		for (Account account : accountList) {
			if (account.getPoint() != 0) {
				String message2 = "現在の保有ポイント：" + account.getPoint() + "   ";
				return message2;
			}
		}
		String message2 = "";
		return message2;
	}

	// 削除
	public void delete(Integer itemId) {
		// 現在のカートから同じ教科書を探す
		for (Item item : itemList) {
			// 対象の教科書があった場合は削除
			if (item.getId() == itemId) {
				itemList.remove(item);
				break;
			}
		}
	}

	// カートの中身をクリア
	public void clear() {
		itemList = new ArrayList<>();
	}
}
