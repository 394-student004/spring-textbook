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
	private List<Item> itemList = new ArrayList<>(); //教科書リスト 

	//アクセッサ
	public List<Item> getItemList() {
		return itemList;
	}

	//合計金額取得用
	public Integer getTotalPrice() {
		//合計金額
		Integer total = 0;
		for (Item item : itemList) {
			total += item.getPrice() * item.getQuantity();
		}
		// 送料追加計算
		if (total == 0) {
			return total;
		} else if (total < 5000) {
			Integer fee = 550;
			total += fee;
		}
		return total;
	}

	//ここから片山の遊び場
	//合計金額から割る50して1ポイントを付与
	/*
	public Integer getPoint() {
		//		Integer point =0;
		Integer point = getTotalPrice() / 50;
		return point;
	}
	*/
	//ここまで片山の遊び場

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

		//カート内に商品がなかった時は追加
		//存在した場合は個数の追加
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
			//対象の商品IDがあった場合は削除
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
