package me.dailyreward.Utils;

public class Time {

	public static String getTime(long num) {
		int hour = (int) num / 60;
		int minutes = (int) num % 60;
		return hour + "h " + minutes + "m";
	}

}
