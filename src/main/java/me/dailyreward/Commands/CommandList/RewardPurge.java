package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MySQL.PlayerMySQL;
import me.dailyreward.Utils.Color;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class RewardPurge extends BaseCommand {

	private final DailyReward plugin = DailyReward.getInstance();
	private final PlayerMySQL playerData = new PlayerMySQL();

	@Override
	@Command(name = "reward.purge", isAdminOnly = true)
	public void onCommand(CommandArgs args) throws SQLException {
		Player player = args.getPlayer();


		switch (args.getArgs(0).toLowerCase()) {
			case "mongodb":
				break;
			case "mysql":
				playerData.dropTable();
				break;
			default:
				Color.sendMessage("MESSAGE_WRONG_ARGS", player);
		}

		Color.sendMessage(plugin.getPrefix() + plugin.getCfg().getString("MESSAGE_SUCCESS_PURGE"), player);
	}

}
