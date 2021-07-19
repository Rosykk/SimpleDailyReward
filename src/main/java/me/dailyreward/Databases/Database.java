package me.dailyreward.Databases;

import lombok.Getter;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MongoDB.MongoDB;
import me.dailyreward.Databases.MySQL.MySQL;

import java.sql.SQLException;

@Getter
public class Database {

	private final DailyReward plugin = DailyReward.getInstance();
	private final Config config = this.plugin.getCfg();

	private MongoDB mongoDB;
	private MySQL mySQL;

	/* User credentials */
	private final String username = this.plugin.getCfg().getString("CREDENTIALS.USERNAME");
	private final String authDatabase = this.plugin.getCfg().getString("CREDENTIALS.DATABASE");
	private final String password = this.plugin.getCfg().getString("CREDENTIALS.PASSWORD");

	/* Database server */
	private final String host = this.plugin.getCfg().getString("CREDENTIALS.HOST");
	private final int port = this.plugin.getCfg().getConfig().getInt("CREDENTIALS.PORT");

	/* Messages */
	private final String errorMessage = this.plugin.getCfg().getString("MESSAGE_ERROR_MYSQL");
	private final String successMessage = this.plugin.getCfg().getString("MESSAGE_DB_SUCCESS");
	private final String closeDatabase = this.plugin.getCfg().getString("MESSAGE_CLOSE_DATABASE");

	public void connect() throws SQLException {
		switch (config.getString("DATABASE").toUpperCase()) {
			case "MONGODB":
				loadMongo();
				break;
			case "MYSQL":
				loadMySQL();
				break;
			default:
				this.plugin.getPluginLoader().disablePlugin(plugin);
		}
	}

	private void loadMongo() {
		mongoDB = new MongoDB();
		mongoDB.connect();
	}

	private void loadMySQL() throws SQLException {
		mySQL = new MySQL();
		mySQL.connect();
		mySQL.createTables();
	}

	public void disconnect() {
		switch (config.getString("DATABASE").toUpperCase()) {
			case "MONGODB":
				mongoDB.disconnect();
				break;
			case "MYSQL":
				mySQL.disconnect();
				break;
			default:
				this.plugin.getLogger().info("");
				this.plugin.getPluginLoader().disablePlugin(plugin);
		}
	}
}
