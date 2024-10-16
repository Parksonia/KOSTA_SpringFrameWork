package com.kosta.shop.dao;

import java.util.List;
import java.util.Map;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.GOrder;
import com.kosta.shop.dto.Goods;
import com.kosta.shop.dto.OrderInfo;

public interface ShopDao {

	List<Goods> allGoodsList() throws Exception;
	Goods oneGoodsList(String gCode) throws Exception;
	Cart selectCart(Integer num) throws Exception;
	void addCart(Cart cart) throws Exception;
	List<Cart> allCartList(String userid) throws Exception;
	void deleteMultipleCart (List<Integer>list)throws Exception;
	void deleteCart(Integer num) throws Exception;
	void updateGAmount(Map<String,Integer>param) throws Exception;
	List<Cart> selectCartOrderAll(List<Integer>list) throws Exception;
	
	//주문
	void insertOrder(GOrder gOrder) throws Exception;
	void insertOrderInfo(OrderInfo orderInfo) throws Exception;
	
}
