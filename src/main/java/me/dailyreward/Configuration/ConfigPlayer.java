package me.dailyreward.Configuration;

import me.dailyreward.DailyReward;
import org.bukkit.entity.Player;

import java.io.File;

public final class ConfigPlayer {

	private static final DailyReward plugin = DailyReward.getInstance();

	public static Yaml getPlayer(Player player) {
		return new Yaml(plugin.getDataFolder().getAbsolutePath() + File.separator + "playerdata" + File.separator + player.getUniqueId() + ".yml");
	}

	public static Yaml getOfflinePlayer(String string) {
		return new Yaml(plugin.getDataFolder().getAbsolutePath() + File.separator + "playerdata" + File.separator + string + ".yml");
	}
}
