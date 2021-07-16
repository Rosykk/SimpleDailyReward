package me.dailyreward.Events;

import me.dailyreward.Configuration.ConfigPlayer;
import me.dailyreward.Configuration.Yaml;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MySQL.PlayerMySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class JoinEvent implements Listener {

	private final DailyReward plugin;
	private Yaml config;

	public JoinEvent(DailyReward plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
		Player player = event.getPlayer();
		config = ConfigPlayer.getPlayer(player); // Get local path

		switch (config.getString("DATABASE").toUpperCase()) {
			case "MONGODB":
				break;
			case "MYSQL":
				PlayerMySQL playerData = new PlayerMySQL();
				playerData.insertPlayer(player);
				break;
			case "LOCAL":
				config.add("DEFAULT", 0);
				config.add("AMAZING", 0);
				config.add("LION", 0);
				config.add("CRUEL", 0);

				config.save(); // Save data
			default:
				plugin.getLogger().info(config.getString("MESSAGE_WRONG_TYPE"));
		}
	}
}
