package com.fightorder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 发送消息定时任务
 * @author James
 *
 */
public abstract class MessageSender {

	/**
	 * 启动一个线程去执行定时任务
	 */
	private ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(1);  
	
	
	protected IWorkerOrderService orderService;
	
	/**
	 * 执行的次数，每执行一次该数量会加1
	 */
	public int num = 1;
	
	/**
	 * 订单id
	 */
	public long orderId;
	
	/**
	 * 启动任务
	 */
	public void start(){  
	    Runnable task = new Runnable() {  
	        public void run() {  
	        	send();
	        	num+=1;
	        }
	    };
	    scheduExec.scheduleWithFixedDelay(task, 0, 3*1000, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 关闭任务
	 */
	public void shutdown(){
		scheduExec.shutdownNow();
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public IWorkerOrderService getOrderService() {
		return orderService;
	}


	public void setOrderService(IWorkerOrderService orderService) {
		this.orderService = orderService;
	}
	
	
	public MessageSender(long orderId, IWorkerOrderService orderService){
		this.setOrderService(orderService);
		this.orderId = orderId;
		start();
	}
	

	/**
	 * 调用通知发送方法
	 */
	public abstract void send();
}
