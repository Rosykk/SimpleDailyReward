package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Databases.MongoDB.PlayerData;

import java.sql.SQLException;

public class RewardRemove extends BaseCommand {

	private PlayerData mongoData;
	private me.dailyreward.Databases.MySQL.PlayerData playerData = new me.dailyreward.Databases.MySQL.PlayerData();

	@Override
	@Command(name = "reward.purge", isAdminOnly = true)
	public void onCommand(CommandArgs args) throws SQLException {

	}
}
