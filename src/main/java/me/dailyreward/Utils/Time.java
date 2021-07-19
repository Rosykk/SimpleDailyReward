package me.dailyreward.Utils;

import lombok.Getter;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class Time {

	private final DailyReward plugin = DailyReward.getInstance();
	private final Config config = plugin.getCfg();

	private final ZoneId zoneId = ZoneId.systemDefault();
	private final ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
	private final Duration duration = Duration.between(currentTime.toLocalDate().atStartOfDay(zoneId), currentTime);
	private final long timeLeft = duration.toMillis();

	@Getter
	private final long day = System.currentTimeMillis() + (86400000L * this.config.getConfig().getInt("DAYS_TO_WAIT"));

	@Getter
	private final long midnight = System.currentTimeMillis() + ((86400000L * config.getConfig().getInt("DAYS_TO_WAIT")) - timeLeft);

	public String getTimeFormat(long num) {
		String format;
		int hour = (int) num / 60;
		int minutes = (int) num % 60;
		return hour + "h " + minutes + "m";
	}

	public long getTime() {
		switch (config.getString("TIME_FORMAT").toUpperCase()) {
			case "DAY":
				return getDay();
			case "MIDNIGHT":
				return getMidnight();
			default:
				plugin.getLogger().info(Color.colorize(config.getString("MESSAGE_ERROR")));
				plugin.getPluginLoader().disablePlugin(plugin);
		}
		return 0;
	}
}
