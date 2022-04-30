package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;
import me.dailyreward.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Reward extends BaseCommand {

	private final DailyReward plugin = DailyReward.getInstance();

	@Override
	@Command(name = "reward")
	public void onCommand(CommandArgs args) throws SQLException {
		Player player = args.getPlayer();
		Config config = this.plugin.getConfiguration();

		if (player.hasPermission(config.getString("DEFAULT_REWARD.PERMISSION"))) {

			long playerTime = this.plugin.getTime().getSavedTime(player, "default");

			if ( playerTime > System.currentTimeMillis() ) {
				Color.sendMessage(config.getString("MESSAGE_NOT_YET").
						replace("%time%", this.plugin.getTime().getTimeFormat(playerTime)), player);
				return;
			}

			// add check for args
			for(String s : config.getStringList("DEFAULT_REWARD.COMMANDS")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%player%", player.getName()));
			}

			Color.sendMessage(this.plugin.getPrefix() + config.getString("DEFAULT_REWARD.MESSAGE"), player);
			this.plugin.getTime().updateTime("default", player);

		} else Color.sendMessage(config.getString("MESSAGE_NOT_ENOUGH_PERMISSIONS"), player);
	}
}
