package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;
import me.dailyreward.Utils.Color;
import me.dailyreward.Utils.Util;

import java.sql.SQLException;

public class RewardReload extends BaseCommand {

	private final DailyReward plugin = DailyReward.getInstance();
	private final Config config = DailyReward.getInstance().getConfiguration();

	@Override
	@Command(name = "reward.reload", isGameOnly = false, isAdminOnly = true)
	public void onCommand(CommandArgs args) throws SQLException {

		if ( args.length() > 1 ) {
			Color.sendMessage(config.getString("MESSAGE_ARGS_ERROR").replace("%command%", "/reward reload"), args.getSender());
			return;
		}

		this.plugin.getConfiguration().loadConfiguration();
		this.plugin.getDatabase().reload();

		Color.sendMessage(config.getString("MESSAGE_RELOAD"), args.getSender());
	}
}
