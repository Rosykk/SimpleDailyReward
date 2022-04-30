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

public record JoinEvent(DailyReward plugin) implements Listener {

	public JoinEvent(DailyReward plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
		Player player = event.getPlayer();

		switch (this.plugin.getConfiguration().getString("DATABASE")) {
			case "MYSQL" -> {
				PlayerMySQL playerData = new PlayerMySQL();
				playerData.insertPlayer(player);
			}
			case "LOCAL" -> {
				Yaml playerConfig = ConfigPlayer.getPlayer(player);

				playerConfig.add("DEFAULT", 0);
				playerConfig.add("AMAZING", 0);
				playerConfig.add("LION", 0);
				playerConfig.add("CRUEL", 0);
				playerConfig.save();
			}
		}
	}
}
