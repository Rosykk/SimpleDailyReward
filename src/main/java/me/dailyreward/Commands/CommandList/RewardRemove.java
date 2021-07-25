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
	private Yaml playerConfig;

	@Override
	@Command(name = "reward.remove", isAdminOnly = true, isGameOnly = false)
	public void onCommand(CommandArgs args) throws SQLException {
		Player player = args.getPlayer();
		Player target = Bukkit.getPlayerExact(args.getArgs(1));

		//TODO check for args

		switch (args.getArgs(0).toLowerCase()) {
			case "mongodb":
				break;
			case "mysql":
				removeMySQL(args.getArgs(1), player);
				Color.sendMessage("MESSAGE_SUCCESS_REMOVE", player);
				break;
			case "local":
				playerConfig = ConfigPlayer.getPlayer(target);
				playerConfig.delete();
				break;
		}
	}

	private void removeMySQL(String target, Player player) throws SQLException {
		if (playerMySQL.playerExists(target)) playerMySQL.removePlayer(target);
		else Color.sendMessage(config.getString("MESSAGE_NO_PLAYER"), player);
	}
}
