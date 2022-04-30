package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MySQL.PlayerMySQL;
import me.dailyreward.Utils.Color;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class RewardPurge extends BaseCommand {

	private final DailyReward plugin = DailyReward.getInstance();
	private final PlayerMySQL playerData = new PlayerMySQL();
	private final Config config = DailyReward.getInstance().getConfiguration();


	@Override
	@Command(name = "reward.purge", isAdminOnly = true, isGameOnly = false)
	public void onCommand(CommandArgs args) throws SQLException {
		Player player = args.getPlayer();

		if ( args.length() > 1 ) {
			Color.sendMessage(config.getString("MESSAGE_ARGS_ERROR").replace("%command%", "/reward reload"), args.getSender());
			return;
		}

		if ( "mysql".equals(args.getArgs(0) ) ) {
			playerData.dropTable();
		} else {
			Color.sendMessage("MESSAGE_ARGS_PURGE", player);
		}

		Color.sendMessage(plugin.getPrefix() + plugin.getConfiguration().getString("MESSAGE_SUCCESS_PURGE"), player);
	}
}
