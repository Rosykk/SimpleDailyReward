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

	/**
	 * Local files
	 **/
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
		Player player = event.getPlayer();

		switch (config.getString("")) {
			case "MONGODB":
				break;
			case "MYSQL":
				PlayerMySQL playerData = new PlayerMySQL();
				playerData.insertPlayer(player);
				break;
			default:
				config = ConfigPlayer.getPlayer(player); // Get local path

				config.add("DEFAULT", 0);
				config.add("AMAZING", 0);
				config.add("LION", 0);
				config.add("CRUEL", 0);

				config.save(); // Save data
		}
	}
}
