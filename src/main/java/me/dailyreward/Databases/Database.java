package me.dailyreward.Databases;

import lombok.Getter;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MongoDB.MongoDB;
import me.dailyreward.Databases.MySQL.MySQL;
import me.dailyreward.Utils.Color;

import java.sql.SQLException;

@Getter
public class Database {

	private final DailyReward plugin = DailyReward.getInstance();

	private MongoDB mongoDB;
	private MySQL mySQL;

	/* User credentials */
	private final String username = this.plugin.getCfg().getConfigString("CREDENTIALS.USERNAME");
	private final String authDatabase = this.plugin.getCfg().getConfigString("CREDENTIALS.DATABASE");
	private final String password = this.plugin.getCfg().getConfigString("CREDENTIALS.PASSWORD");

	/* Database server */
	private final String host = this.plugin.getCfg().getConfigString("CREDENTIALS.HOST");
	private final int port = this.plugin.getCfg().getConfig().getInt("CREDENTIALS.PORT");

	private final boolean mongodb = this.plugin.getCfg().getConfigBool("DATABASE.MONGODB");
	private final boolean mysql = this.plugin.getCfg().getConfigBool("DATABASE.MYSQL");

	/* Messages */
	private final String errorMessage = this.plugin.getCfg().getConfigString("MESSAGE_ERROR_MYSQL");
	private final String successMessage = this.plugin.getCfg().getConfigString("MESSAGE_DB_SUCCESS");

	public void connect() throws SQLException {
		if(!illegal()) {
			if (mongodb) {
				mongoDB = new MongoDB();
				mongoDB.connect();
			}

			if(mysql) {
				mySQL = new MySQL();
				mySQL.connect();
				mySQL.createTables();
			}
		}
	}

	public boolean illegal() {
		if (mongodb && mysql) {
			plugin.getLogger().info(Color.colorize("&cDisable MySQL or MondoDB, at the moment is not allowed using both at the same time!"));
			plugin.getPluginLoader().disablePlugin(plugin);
			return true;
		}
		return false;
	}

}
