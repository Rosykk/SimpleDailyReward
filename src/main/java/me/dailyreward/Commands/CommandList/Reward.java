package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;
import me.dailyreward.Utils.Color;
import me.dailyreward.Utils.Util;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Reward extends BaseCommand {

	private final DailyReward plugin = DailyReward.getInstance();

	@Override
	@Command(name = "reward")
	public void onCommand(CommandArgs args) throws SQLException {
		Player player = args.getPlayer();

		Config config = this.plugin.getConfiguration();

		if (Util.argLength(args, 1)) return;

		if (player.hasPermission(config.getString("DEFAULT_REWARD.PERMISSION"))) {

		} else Color.sendMessage(config.getString("MESSAGE_NOT_ENOUGH_PERMISSIONS"), player);
	}
}
