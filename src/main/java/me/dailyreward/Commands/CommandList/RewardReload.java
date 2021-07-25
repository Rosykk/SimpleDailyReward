package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.DailyReward;
import me.dailyreward.Utils.Color;

import java.sql.SQLException;

public class RewardReload extends BaseCommand {

	private final DailyReward plugin = DailyReward.getInstance();

	@Override
	@Command(name = "reward.reload", isGameOnly = false, isAdminOnly = true)
	public void onCommand(CommandArgs args) throws SQLException {

		this.plugin.getDatabase().reload();

		Color.sendMessage(this.plugin.getConfiguration().getString("MESSAGE_RELOAD"), args.getSender());
	}
}
