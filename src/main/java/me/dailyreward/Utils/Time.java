package me.dailyreward.Utils;

import lombok.Getter;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.Config;
import me.dailyreward.Configuration.ConfigPlayer;
import me.dailyreward.Configuration.Yaml;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MySQL.PlayerMySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

@Getter
public class Time {

    private final DailyReward plugin = DailyReward.getInstance();

    private final PlayerMySQL playerMySQL = new PlayerMySQL();
    private final Config config = plugin.getConfiguration();

    private final ZoneId zoneId = ZoneId.systemDefault();
    private final ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
    private final Duration duration = Duration.between(currentTime.toLocalDate().atStartOfDay(zoneId), currentTime);
    private final long timeLeft = duration.toMillis();

    private final long hour = System.currentTimeMillis() + (86400000L * this.config.getConfig().getInt("DAYS_TO_WAIT"));
    private final long midnight = System.currentTimeMillis() + ((86400000L * config.getConfig().getInt("DAYS_TO_WAIT")) - timeLeft);

    public long getTimeType() {
        if ( plugin.getConfiguration().getString("TIME_FORMAT").equals("HOUR") ) {
            return hour;
        }
        if ( plugin.getConfiguration().getString("TIME_FORMAT").equals("MIDNIGHT") ) {
            return midnight;
        }
        return 0L;
    }

    public String getTimeFormat(long time) {
        long date = (time - System.currentTimeMillis()) / 1000 / 60;

        int h = (int) date / 60;
        int m = (int) date % 60;

        return h + "h" + " " + m + "m";
    }

    public long getSavedTime(Player player, String s) throws SQLException {
        switch (config.getString("DATABASE").toUpperCase()) {
            case "LOCAL" -> {
                Yaml playerConfig = ConfigPlayer.getPlayer(player);

                return playerConfig.getLong(s.toUpperCase());
            }
            case "MYSQL" -> {
                return playerMySQL.getStoredTime(s.toLowerCase(), player);
            }
        }
        return 0L;
    }

    public void updateTime(String s, Player player) throws SQLException {
        switch (this.plugin.getConfiguration().getString("DATABASE")) {
            case "MYSQL" -> {
                PlayerMySQL playerData = new PlayerMySQL();

                playerData.updateTime(s.toLowerCase(), plugin.getTime().getTimeType(), player);
            }
            case "LOCAL" -> {
                Yaml playerConfig = ConfigPlayer.getPlayer(player);

                playerConfig.set(s.toUpperCase(), plugin.getTime().getTimeType());
                playerConfig.save();
            }
        }
    }
}
