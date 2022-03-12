package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
@Service
@Scope("session")
public class Cart {
	//상품번호, 상품갯수가 저장됨
	private List<ItemSet> itemList = new ArrayList<ItemSet>();
	
	public List<ItemSet> getItemList(){ return itemList;}//getter생성한것
	public void push(ItemSet pushItemSet) {//장바구니에 과일정보한줄+갯수를 넣는다
		boolean itemExistInCart = false;//이미 장바구니에 담긴 물건인지 검사,false는 장바구니에 없는 상품이라는 뜻
		TradeBBS pushedItem = pushItemSet.getItem();
		int pushedItemId = pushedItem.getWriting_id();//상품번호읽음
		for(ItemSet cartItemSet: this.itemList) {
			TradeBBS cartItem = cartItemSet.getItem();
			int cartItemId = cartItem.getWriting_id();//cartItemId이미 장바구니에 있는 상품번호
			if(cartItemId == pushedItemId) {
				cartItemSet.addQuantity(pushItemSet.getQuantity());//갯수만 증가시킨다
				itemExistInCart = true;//false 가 true로 바뀌면 이미 있는상품이라는뜻
				break;//반복종료 
			}
		}//장바구니에 담는 상품번호가 이미 담긴 상품번호인지  반복해서 검사
		if(! itemExistInCart ) this.itemList.add(pushItemSet);//!가 false를 뜻한다,장바구니에 없으면 넣어주는 메서드
	}
	
	public boolean isEmpty() {//장바구니가 비어있는지 여부를 확이하는 메서드(비어있으면 true,비어있지 않으면 false)
		if(this.itemList == null || this.itemList.isEmpty())
			return true;
		else return false;
	}
	
	public void clearAll() {//장바구니를 비우는 메서드
		this.itemList = new ArrayList<ItemSet>();
	}
}
