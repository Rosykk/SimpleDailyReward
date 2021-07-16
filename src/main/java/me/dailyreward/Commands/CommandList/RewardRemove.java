package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MongoDB.PlayerMongo;
import me.dailyreward.Databases.MySQL.PlayerMySQL;
import me.dailyreward.Utils.Color;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class RewardRemove extends BaseCommand {

	private PlayerMongo mongoData;
	private final PlayerMySQL playerMySQL = new PlayerMySQL();
	private final Config config = DailyReward.getInstance().getCfg();

	@Override
	@Command(name = "reward.remove", isAdminOnly = true)
	public void onCommand(CommandArgs args) throws SQLException {
		Player player = args.getPlayer();
		String target = args.getArgs(1);

		switch (args.getArgs(0)) {
			case "mongodb":
				break;
			case "mysql":
				removeMySQL(target, player);
				Color.sendMessage("MESSAGE_SUCCESS_REMOVE", player);
				break;
		}
	}

	private void removeMySQL(String target, Player player) throws SQLException {
		if (playerMySQL.playerExists(target)) playerMySQL.removePlayer(target);
		else Color.sendMessage(config.getString("MESSAGE_NO_PLAYER"), player);
	}
}
