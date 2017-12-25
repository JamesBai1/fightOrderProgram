package com.fightorder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息发送者调度室
 * @author James
 *
 */
public class MessageSenderDispatchRoom {
	
	/**
	 * 存放每一个订单的发送消息者的定时任务的map，用来控制其关闭
	 */
	private static Map<Long, MessageSender> senderMap = new ConcurrentHashMap<Long, MessageSender>();

	
	/**
	 * 发送者容器添加一个订单通知发送者
	 * @param orderId
	 */
	public void addMessageSender(Long orderId, IWorkerOrderService orderService){
		senderMap.put(orderId, new APPMessageSender(orderId, orderService));
	}
	
	/**
	 * 容器移除一个订单通知发送者
	 * @param orderId
	 */
	public void removeMessageSender(Long orderId){
		MessageSender sender = senderMap.get(orderId);
		if(sender != null){
			sender.shutdown();
			senderMap.remove(orderId);
		}
	}
}
