package me.dailyreward.Commands;

import me.dailyreward.DailyReward;

import java.sql.SQLException;

public abstract class BaseCommand {

	public DailyReward main = DailyReward.getInstance();

	public BaseCommand() {
		main.getFramework().registerCommands(this);
	}

	public abstract void onCommand(CommandArgs command) throws SQLException;

}