package com.kosta.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.GOrder;
import com.kosta.shop.dto.Goods;
import com.kosta.shop.dto.OrderInfo;

@Repository
public class ShopDaoImpl implements ShopDao {

	@Autowired
	private SqlSession sqlsession;

	@Override
	public List<Goods> allGoodsList() throws Exception {
		List<Goods> allgoodslists = sqlsession.selectList("mapper.goods.selectAllGoodsList");
		
		return allgoodslists;
	}

	@Override
	public Goods oneGoodsList(String gCode) throws Exception {
		
		return sqlsession.selectOne("mapper.goods.selectOneGoodsList",gCode);
	}

	@Override
	public Cart selectCart(Integer num) throws Exception {
		Cart scart = sqlsession.selectOne("mapper.cart.selectCart",num);
		return scart;
	}

	@Override
	public void addCart(Cart cart) throws Exception {
		sqlsession.insert("mapper.cart.insertAddCart", cart);
		
	}

	@Override
	public List<Cart> allCartList(String userid) throws Exception {
		
		return sqlsession.selectList("mapper.cart.selectCartList", userid);
	}

	@Override
	public void deleteMultipleCart(List<Integer> list) throws Exception {
		sqlsession.delete("mapper.cart.deleteMultiCart", list);
		
	}

	@Override
	public void deleteCart(Integer num) throws Exception {
		sqlsession.delete("mapper.cart.deleteOneCart", num);	
	}

	@Override
	public void updateGAmount(Map<String, Integer> param) throws Exception {
		sqlsession.update("mapper.cart.updateGAmount", param);	
	}

	@Override
	public List<Cart> selectCartOrderAll(List<Integer> list) throws Exception {
		
		return sqlsession.selectList("mapper.cart.selectCartOrderAllList", list);
	}

	//주문처리
	@Override
	public void insertOrder(GOrder gOrder) throws Exception {
		sqlsession.insert("mapper.cart.insertOrder", gOrder);
		
	}

	@Override
	public void insertOrderInfo(OrderInfo orderInfo) throws Exception {
		sqlsession.insert("mapper.cart.insertOrderInfo", orderInfo);
		
	}



	
	
}
