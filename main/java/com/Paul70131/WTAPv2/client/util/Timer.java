package com.Paul70131.WTAPv2.client.util;

public class Timer {

	private long delay;
	private long lastTime = 0;
	private long randDelay;
	
	public boolean checkTimer() {
		if ((System.nanoTime() / 1000000 - this.lastTime) > this.randDelay) {
			return true;
		} else {
			return false;
		}
	}
	public void resetDelay(double value) {
		this.lastTime = (System.nanoTime() / 1000000);
		this.randDelay = (long) value;
	}
	public void resetRandDelay(double value) {
		this.lastTime = (System.nanoTime() / 1000000);
		this.randDelay = (long) (value + ((Math.random() * 10) - 5));
	}
}
