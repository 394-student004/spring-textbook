package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

@Component
@SessionScope
public class Cart {
	//フィールド
	private List<Item> itemList = new ArrayList<>(); //商品リスト 

	//アクセッサ(商品リストゲッター)
	public List<Item> getItemList() {
		return itemList;
	}

	//合計金額取得用ゲッター
	public Integer getTotalPrice() {
		//合計金額
		Integer total = 0;
		for (Item item : itemList) {
			total += item.getPrice() * item.getQuantity();
		}
		if (total < 5000) {
			Integer fee = 550;
			total += fee;
		}
		return total;
	}

	//カート追加
	public void add(Item newItem) {
		Item existsItem = null;
		//現在のカートの商品から同一IDの商品を探す
		for (Item item : itemList) {
			if (item.getId() == newItem.getId()) {
				existsItem = item;
				break;
			}
		}

		//カート内に商品なかった時に追加(if)
		//存在した場合、個数の更新(else)
		if (existsItem == null) {
			itemList.add(newItem);
		} else {
			existsItem.setQuantity(
					existsItem.getQuantity() + newItem.getQuantity());
		}
	}

	//削除
	public void delete(Integer itemId) {
		//現在のカートから同一IDの商品を探す
		for (Item item : itemList) {
			//対象の商品IDがあった場合削除
			if (item.getId() == itemId) {
				itemList.remove(item);
				break;
			}
		}
	}

	//カートの中身をクリア
	public void clear() {
		itemList = new ArrayList<>();
	}
}
