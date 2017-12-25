package com.fightorder;

import java.util.concurrent.DelayQueue;

/**
 * 重载remove接口
 * @author James
 *
 */
public class OrderDelayQueue extends DelayQueue<Order>{

	@Override
	public boolean remove(Object arg0) {
		Order sender = (Order)arg0;
		Object [] arr = this.toArray();
		if(arr != null){
			for(int i = 0; i < arr.length; i++){
				Order o = (Order) arr[i];
				if(o.getOrderId() == sender.getOrderId()){
					return super.remove(o);
				}
			}
		}
		return false;
	}
	
	/**
	 * 根据订单id删除元素
	 * @param orderId
	 * @return
	 */
	public boolean removeByOrderId(long orderId){
		Object [] arr = this.toArray();
		if(arr != null){
			for(int i = 0; i < arr.length; i++){
				Order o = (Order) arr[i];
				if(o.getOrderId() == orderId){
					return super.remove(o);
				}
			}
		}
		return false;
	}
}
