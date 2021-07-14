package me.dailyreward.Databases.MongoDB;

public class Stats {

	private int time;

	/* MongoDB time */
	public void incereaseTime(int time) {
		this.time += time;
	}

	public void decreaseTime(int time) {
		this.time += time;
	}

	public int getAmount() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
