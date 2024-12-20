package com.kosta.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.ShopDao;
import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.GOrder;
import com.kosta.shop.dto.Goods;
import com.kosta.shop.dto.OrderInfo;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopdao;
	
	@Override
	public List<Goods> goodsLists() throws Exception {
	
		return shopdao.allGoodsList();
	}

	//하나 조회 할 때 List가 아니라 selectOne!!!!!!!!!
	@Override
	public Goods goodsDetail(String gCode) throws Exception {
		
		return shopdao.oneGoodsList(gCode);
	}

	@Override
	public boolean addCartList(Cart cart) throws Exception {
		
		Cart scart = shopdao.selectCart(cart.getNum());
		if(scart == null) {			
			shopdao.addCart(cart);
			return true;
		}
		return false;
	}

	@Override
	public List<Cart> allCartList(String userid) throws Exception {	
		return shopdao.allCartList(userid);
	}

	@Override
	public void deleteMultiItemCart(List<Integer> list) throws Exception {
	
		 shopdao.deleteMultipleCart(list);
	}

	@Override
	public void deleteOneItemCart(Integer num) throws Exception {
		shopdao.deleteCart(num);
	}

	@Override
	public void cartUpdateAmount(Integer num, Integer gAmount) throws Exception {
		Map<String,Integer> param = new HashMap<>();
		param.put("num", num);
		param.put("gAmount", gAmount);
		
		shopdao.updateGAmount(param);
	}

	@Override
	public Cart confirmCartOrderList(Integer num) throws Exception {
		Cart orderItem = shopdao.selectCart(num);
		
		return orderItem;
	}

	// 다중 선택 주문 
	@Override
	public List<Cart> confirmCartOrderListAll(List<Integer> list) throws Exception {
	
		return shopdao.selectCartOrderAll(list);
	}

	@Override
	public void orderDone(OrderInfo orderInfo, GOrder gOrder, Integer orderNum) throws Exception {
	
		try {
			shopdao.insertOrderInfo(orderInfo);
			// Gorder 는 order_infoNum을 가진다.
			gOrder.setOrderinfo_num(orderInfo.getNum());
			shopdao.insertOrder(gOrder);
			
			// 주문 완료되면 카트삭제
			if(orderNum !=null) {
				shopdao.deleteCart(orderNum);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<GOrder>  orderAllDone(OrderInfo orderInfo, List<Integer> nums) throws Exception {
	
		List<GOrder> gOrderList = new ArrayList<>();
			
		try {
			shopdao.insertOrderInfo(orderInfo);
		
			// 카트에서 조회해서 gOrder에 넣기
			List<Cart> selectCarts = shopdao.selectCartOrderAll(nums);
			for(Cart cart: selectCarts) {
				GOrder gOrder = new GOrder();
				gOrder.setgCode(cart.getgCode());
				gOrder.setgName(cart.getgName());
				gOrder.setgAmount(cart.getgAmount());
				gOrder.setgColor(cart.getgColor());
				gOrder.setgImage(cart.getgImage());
				gOrder.setgPrice(cart.getgPrice());
				gOrder.setgSize(cart.getgSize());
				gOrder.setNum(cart.getNum());
				gOrder.setUserid(cart.getUserid());
				//orderInfo에서 orderNum가져오기
				gOrder.setOrderinfo_num(orderInfo.getNum());
				
				shopdao.insertOrder(gOrder);
				//shopdao.deleteCart(cart.getNum());
				gOrderList.add(gOrder);
			}
				shopdao.deleteMultipleCart(nums);
				return gOrderList;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gOrderList;
		
	}
}
