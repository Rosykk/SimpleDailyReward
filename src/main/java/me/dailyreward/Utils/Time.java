package me.dailyreward.Utils;

import lombok.Getter;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;

import java.util.Date;

@Getter
public class Time {

	private static DailyReward plugin = DailyReward.getInstance();
	private static Config config = plugin.getCfg();

	private static Date date = new Date();

	@Getter
	private static final long format1 = System.currentTimeMillis() + 86400000L;

	// does not work, add to config some other stuff
	private static final long format2 = System.currentTimeMillis() + (86400000L - date.getTime());

	public static String getTimeFormat(long num) {
		int hour = (int) num / 60;
		int minutes = (int) num % 60;
		return hour + "h " + minutes + "m";
	}

}
