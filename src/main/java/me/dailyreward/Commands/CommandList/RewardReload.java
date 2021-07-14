package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.ConfigManager;
import me.dailyreward.Utils.Color;
import org.bukkit.configuration.file.FileConfiguration;

public class RewardReload extends BaseCommand {

	@Override
	@Command(name = "reward.reload", isGameOnly = false, isAdminOnly = true)
	public void onCommand(CommandArgs args) {
		ConfigManager.reloadConfig();
		FileConfiguration config = ConfigManager.getConfig();
		Color.sendMessage(config.getString("MESSAGE_RELOAD"), args.getSender());
	}
}
