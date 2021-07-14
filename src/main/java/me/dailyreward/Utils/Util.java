package me.dailyreward.Utils;

import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.ConfigManager;
import me.dailyreward.DailyReward;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Objects;

public class Util {

	private final DailyReward plugin = DailyReward.getInstance();

	private final boolean mongodb = this.plugin.getCfg().getConfigBool("MONGODB.ENABLED");
	private final boolean mysql = this.plugin.getCfg().getConfigBool("MYSQL.ENABLED");

	public static boolean argLength(CommandArgs args, int num) {
		if(args.length() >= num) {
			Color.sendMessage(ConfigManager.getConfig().getString("MESSAGE_WRONG_ARGS"), args.getPlayer());
			return true;
		}
		return false;
	}

	public static String nonNull(ConfigurationSection section, String s) {
		return Objects.requireNonNull(section.getString(s));
	}





}
