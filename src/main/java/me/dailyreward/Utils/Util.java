package me.dailyreward.Utils;

import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.ConfigManager;
import me.dailyreward.DailyReward;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Objects;

public class Util {

	public static boolean argLength(CommandArgs args, int num) {
		if(args.length() >= num) {
			Color.sendMessage(ConfigManager.getConfig().getString("MESSAGE_WRONG_ARGS"), args.getPlayer());
			return true;
		}
		return false;
	}
}
