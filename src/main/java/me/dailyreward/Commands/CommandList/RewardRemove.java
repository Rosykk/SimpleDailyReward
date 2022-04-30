package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.Config;
import me.dailyreward.Configuration.ConfigPlayer;
import me.dailyreward.Configuration.Yaml;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MySQL.PlayerMySQL;
import me.dailyreward.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class RewardRemove extends BaseCommand {

	private final PlayerMySQL playerMySQL = new PlayerMySQL();
	private final Config config = DailyReward.getInstance().getConfiguration();

	@Override
	@Command(name = "reward.remove", isAdminOnly = true, isGameOnly = false)
	public void onCommand(CommandArgs args) throws SQLException {
		Player player = args.getPlayer();
		Player target = Bukkit.getPlayerExact(args.getArgs(1));

		if ( args.length() > 1 ) {
			Color.sendMessage(config.getString("MESSAGE_ARGS_ERROR").replace("%command%", "/reward remove"), player);
			return;
		}

		switch (args.getArgs(0).toUpperCase()) {
			case "MYSQL" -> {
				removeMySQL(args.getArgs(1), player);
				Color.sendMessage(config.getString("MESSAGE_SUCCESS_REMOVE"), player);
			}
			case "LOCAL" -> {
				// TODO may not work
				Yaml playerConfig = ConfigPlayer.getPlayer(target);

				if ( playerConfig.toString() != null ) {
					playerConfig.delete();
					Color.sendMessage(config.getString("MESSAGE_SUCCESS_REMOVE"), player);
				}
				else {
					Color.sendMessage(config.getString("MESSAGE_NO_PLAYER"), player);
				}
			}
		}
	}

	private void removeMySQL(String target, Player player) throws SQLException {
		if (playerMySQL.playerExists(target)) playerMySQL.removePlayer(target);
		else Color.sendMessage(config.getString("MESSAGE_NO_PLAYER"), player);
	}
}
