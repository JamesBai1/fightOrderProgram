package com.fightorder;

public class Worker {

	
	/**
	 * 服务人员抢单
	 * @param orderId
	 * @return
	 */
	public boolean fight(long orderId){
		OrderDispatchRoom room = new OrderDispatchRoom();
		return room.removeOrder(1l);//队员触发抢单行为
	}
}
