package com.fightorder;



/**
 * APP端消息发送实现类
 * @author James
 *
 */
public class APPMessageSender extends MessageSender{
	
	
	
	public APPMessageSender(long orderId, IWorkerOrderService orderService){
		super(orderId, orderService);
	}


	@Override
	public void send() {
		//发送通知的逻辑
	}
}
