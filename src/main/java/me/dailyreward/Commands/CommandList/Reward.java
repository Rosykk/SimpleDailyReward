package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.ConfigPlayer;
import me.dailyreward.Configuration.Yaml;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MySQL.PlayerMySQL;
import me.dailyreward.Utils.Color;
import me.dailyreward.Utils.DB;
import me.dailyreward.Utils.Time;
import me.dailyreward.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reward extends BaseCommand {

	private final DailyReward plugin = DailyReward.getInstance();
	private final PlayerMySQL playerMySQL = new PlayerMySQL();

	@Override
	@Command(name = "reward." )
	public void onCommand(CommandArgs args) throws SQLException {
		Player player = args.getPlayer();

		Yaml config = ConfigPlayer.getPlayer(player);

		if(Util.argLength(args, 1)) return;

		/* Check if player has permission and and if it is not NULL */
		if(player.hasPermission("DEFAULT_REWARD.PERMISSION")) {
			if(config.getLong("DEFAULT_REWARD.DEFAULT") < System.currentTimeMillis()) {
				List<String> list = new ArrayList<>(config.getStringList("DEFAULT_REWARD.COMMANDS"));
				ConsoleCommandSender sender = Bukkit.getConsoleSender();

				for(String s : list) {
					Bukkit.dispatchCommand(sender, s.replace("%player%", player.getDisplayName()));
				}

				Color.sendMessage(plugin.getPrefix() +  config.getString("DEFAULT_REWARD.MESSAGE"), player);

				/* Saves a new value to the path (local files)*/
				if(DB.getLocalFiles()) {
					config.set("DEFAULT", Time.getFormat1());
					config.save();
				}

				/* Saves a new value to the path (MySQL database)*/
				if(DB.getMySQL()) playerMySQL.updateTime("default", Time.getFormat1(), player);

				/* Saves a new value to the path (MongoDB bson)*/
				//if(DB.getLocalFiles()) { }

			} else {
				long time = (config.getLong("DEFAULT") - System.currentTimeMillis()) / 1000 / 60;
				Color.sendMessage(plugin.getCfg().getString("MESSAGE_NOT_YET").replace("%time%", Time.getTimeFormat(time)), player);
			}
		} else Color.sendMessage(plugin.getCfg().getString("MESSAGE_NOT_ENOUGH_PERMISSIONS"), player);
	}


}
