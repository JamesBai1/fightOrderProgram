package com.fightorder;

public class FightTest {

	
	public static void main(String[] args) {
		User u = new User();
		u.createOrder(1l);
		
		Worker w = new Worker();
		w.fight(1l);
	}
}
