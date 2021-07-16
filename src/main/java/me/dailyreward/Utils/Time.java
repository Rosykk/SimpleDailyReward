package me.dailyreward.Utils;

import lombok.Getter;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class Time {

	private static final DailyReward plugin = DailyReward.getInstance();
	private static final Config config = plugin.getCfg();

	private static final ZoneId zoneId = ZoneId.systemDefault();
	private static final ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
	private static final Duration duration = Duration.between(currentTime.toLocalDate().atStartOfDay(zoneId), currentTime);
	private static final long timeLeft = duration.toMillis();

	@Getter
	private static final long format1 = System.currentTimeMillis() + (86400000L * config.getConfig().getInt("DAYS_TO_WAIT"));

	@Getter
	private static final long format2 = System.currentTimeMillis() + ((86400000L * config.getConfig().getInt("DAYS_TO_WAIT")) - timeLeft);

	public static String getTimeFormat(long num) {
		int hour = (int) num / 60;
		int minutes = (int) num % 60;
		return hour + "h " + minutes + "m";
	}
}
