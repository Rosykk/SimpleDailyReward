package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Databases.MySQL.PlayerData;

import java.sql.SQLException;

public class RewardPurge extends BaseCommand {

	private PlayerData playerData = new PlayerData();

	@Override
	@Command(name = "reward.purge", isAdminOnly = true)
	public void onCommand(CommandArgs args) throws SQLException {
		switch (args.getArgs(0)) {
			case "mongodb":

			case "mysql":
				playerData.dropTable();
			case "local":

		}
	}

}
