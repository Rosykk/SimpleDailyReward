package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.ConfigPlayer;
import me.dailyreward.Configuration.Yaml;
import me.dailyreward.DailyReward;
import me.dailyreward.Utils.Color;
import me.dailyreward.Utils.Time;
import me.dailyreward.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RewardLion extends BaseCommand {

	private final DailyReward plugin = DailyReward.getInstance();

	@Override
	@Command(name = "reward.lion")
	public void onCommand(CommandArgs args) {
		Player player = args.getPlayer();

		Yaml config = ConfigPlayer.getPlayer(player);

		if(Util.argLength(args, 1)) return;

		/* Check if player has permission and and if it is not NULL */
		if(player.hasPermission("REWARD_LION.PERMISSION")) {
			if(config.getLong("LION") < System.currentTimeMillis()) {
				List<String> list = new ArrayList<>(config.getStringList("REWARD_LION.COMMANDS"));
				ConsoleCommandSender sender = Bukkit.getConsoleSender();

				for(String s : list) {
					Bukkit.dispatchCommand(sender, s.replace("%player%", player.getDisplayName()));
				}

				Color.sendMessage(plugin.getPrefix() + config.getString("REWARD_LION.MESSAGE"), player);

				/* Saves a new value to the path */
				config.set("LION", Time.getFormat1());
				config.save();
			} else {
				long time = (config.getLong("LION") - System.currentTimeMillis()) / 1000 / 60;
				Color.sendMessage(plugin.getCfg().getString("MESSAGE_NOT_YET").replace("%time%", Time.getTimeFormat(time)), player);
			}
		} else Color.sendMessage(plugin.getCfg().getString("MESSAGE_NOT_ENOUGH_PERMISSIONS"), player);
	}
}
