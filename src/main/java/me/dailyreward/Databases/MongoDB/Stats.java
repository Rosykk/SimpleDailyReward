package me.dailyreward.Databases.MongoDB;

public class Stats {

	private long time;

	/* MongoDB time */
	public void increaseTime(long time) {
		this.time += time;
	}

	public void decreaseTime(long time) {
		this.time += time;
	}

	public long getAmount() {
		return this.time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
