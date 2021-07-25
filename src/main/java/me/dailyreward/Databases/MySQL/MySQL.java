package me.dailyreward.Databases.MySQL;

import me.dailyreward.DailyReward;
import me.dailyreward.Databases.Database;
import me.dailyreward.Utils.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

	private final DailyReward plugin = DailyReward.getInstance();
	private final Database db = new Database();

	/* MySQL connection */
	private Connection connection;

	public boolean isConnected() {
		return connection != null;
	}

	public void connect() throws SQLException {
		if (!isConnected()) {
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + this.db.getHost() + ":" + this.db.getPort() +
							"/" + this.db.getAuthDatabase() + "?useSSL=false",
					this.db.getUsername(), db.getPassword());
			plugin.getLogger().info(Color.colorize(this.plugin.getConfiguration().getString("MESSAGE_DB_SUCCESS")));
		}
	}

	public void disconnect() {
		if(isConnected()) {
			try {
				connection.close();
				plugin.getLogger().info(Color.colorize(this.plugin.getConfiguration().getString("MESSAGE_DB_DISCONNECT")));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createTables() throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement(
				"CREATE TABLE IF NOT EXISTS `dailyreward` (" +
						"  `id` int(50) NOT NULL AUTO_INCREMENT," +
						"  `player` varchar(30) NOT NULL," +
						"  `default` bigint(23) unsigned DEFAULT 0," +
						"  `amazing` bigint(23) unsigned DEFAULT 0," +
						"  `cruel` bigint(23) unsigned DEFAULT 0," +
						"  `lion` bigint(23) unsigned DEFAULT 0," +
						"  PRIMARY KEY (`id`)" +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

		ps.executeUpdate();
	}

	public Connection getConnection() {
		return connection;
	}
}
