package com.fightorder;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单队列内的订单对象
 * @author James
 *
 */
public class Order implements Delayed{
	
	/**
	 * 订单id
	 */
	private long orderId;
	
	/**
	 * 结束时间
	 */
	private long endTime;
	
	
	/**
	 * 1-前台；2-后台
	 */
	private int platform;
	
	
	/**
	 * 数据源
	 */
	private String dbKey;
	
	public Order(long orderId, long endTime, int platform, String dbKey){
		this.orderId = orderId;
		this.endTime = endTime;
		this.platform = platform;
		this.dbKey = dbKey;
	}
	
	
	public Order(long orderId, long endTime, int platform){
		this.orderId = orderId;
		this.endTime = endTime;
		this.platform = platform;
	}

	public int compareTo(Delayed obj) {
		Order sender = (Order)obj;
		return endTime - sender.endTime > 0 ? 1 : 0;
	}

	public long getDelay(TimeUnit arg0) {
		return endTime - System.currentTimeMillis();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getPlatform() {
		return platform;
	}

	public String getDbKey() {
		return dbKey;
	}

	public void setDbKey(String dbKey) {
		this.dbKey = dbKey;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}
}
