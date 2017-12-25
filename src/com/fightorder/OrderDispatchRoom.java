package com.fightorder;



/**
 * 订单队列的调度室
 * @author James
 *
 */
public class OrderDispatchRoom{
	
	
	/**
	 * 等待的时间长度
	 */
	private long timeLengh = 1000*1*60;
	
	
	/**
	 * 存储预约单号的队列
	 */
	private static OrderDelayQueue senders = new OrderDelayQueue();
	
	/**
	 * 启动标记
	 */
	private static boolean start = false;
	
	//辅助业务接口
	private IWorkerOrderService orderService;
	
	
	private MessageSenderDispatchRoom messageRoom;
	
	/**
	 * 启动器
	 */
	private Thread engine = new Thread(){
		public void run() {
			while(start){
				try {
					Order sender = senders.take();
					if(sender != null){
						automaticSelectWorker(sender.getOrderId(), sender.getPlatform());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	/**
	 * 新增一个通知发送者和一个订单
	 */
	public void createOrder(long orderId, int platform){
		if(!start) startup();
		if(orderId <= 0) return;
		Order order = new Order(orderId, timeLengh + System.currentTimeMillis(), platform);
		senders.put(order);
		messageRoom.addMessageSender(orderId, orderService);
	}
	
	/**
	 * 启动订单队列引擎
	 */
	public synchronized void startup(){
		if(start){
			return;
		}
		start = true;
		engine.start();
	}
	
	/**
	 * 停止订单队列引擎
	 */
	public synchronized void shutdown(){
		if(!start){
			return;
		}
		start = false;
		engine.interrupt();
	}
	
	/**
	 * 从队列中移除出该订单
	 * @param orderId
	 * @return
	 */
	public synchronized boolean removeOrder(long orderId){
		boolean flag = senders.remove(new Order(orderId, 0l, 1));
		if(flag){
			//当成功移除之后，要同时停止消息发送
			messageRoom.removeMessageSender(orderId);
			return true;
		}
		return false;
	}
	


	/**
	 * 2分钟之后依然可以取出，说明未有人接单，则需要执行自动安排人员逻辑
	 * 查询该时间段没有预约的服务人员，随机选择一个生成订单
	 * @param orderId
	 */
	private void automaticSelectWorker(long orderId, int platform){
		messageRoom.removeMessageSender(orderId);
		//执行无人抢单逻辑
	}
}
