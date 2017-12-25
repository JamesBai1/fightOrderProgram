package com.fightorder;

public class User {

	/**
	 * 客户下单
	 * @param orderId
	 * @return
	 */
	public boolean createOrder(long orderId){
		OrderDispatchRoom room = new OrderDispatchRoom();
		room.createOrder(1l, 1);//客户发送订单需求
		return true;
	}
}
