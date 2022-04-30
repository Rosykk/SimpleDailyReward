package me.dailyreward.Databases;

import lombok.Getter;
import me.dailyreward.Configuration.Config;
import me.dailyreward.Configuration.ConfigManager;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MySQL.MySQL;

import java.sql.SQLException;

@Getter
public class Database {

	private final DailyReward plugin = DailyReward.getInstance();
	private final Config config = this.plugin.getConfiguration();

	private MySQL mySQL;

	/* User credentials */
	private final String username = this.plugin.getConfiguration().getString("CREDENTIALS.USERNAME");
	private final String authDatabase = this.plugin.getConfiguration().getString("CREDENTIALS.DATABASE");
	private final String password = this.plugin.getConfiguration().getString("CREDENTIALS.PASSWORD");

	/* Database server */
	private final String host = this.plugin.getConfiguration().getString("CREDENTIALS.HOST");
	private final int port = this.plugin.getConfiguration().getConfig().getInt("CREDENTIALS.PORT");

	public void connect() throws SQLException {
		switch (config.getString("DATABASE").toUpperCase()) {
			case "MYSQL":
				loadMySQL();
				break;
			case "LOCAL":
				break;
			default:
				this.plugin.getPluginLoader().disablePlugin(plugin);
		}
	}

	public void disconnect() {
		if ("MYSQL".equals(config.getString("DATABASE"))) {
			mySQL.disconnect();
		} else {
			this.plugin.getPluginLoader().disablePlugin(plugin);
		}
	}

	public void reload() throws SQLException {
		ConfigManager.reloadConfig();

		if (config.getString("DATABASE").equals("MYSQL")) {
			mySQL.disconnect();

			mySQL = new MySQL();
			mySQL.connect();
		}
	}

	private void loadMySQL() throws SQLException {
		mySQL = new MySQL();
		mySQL.connect();
		mySQL.createTables();
	}
}
