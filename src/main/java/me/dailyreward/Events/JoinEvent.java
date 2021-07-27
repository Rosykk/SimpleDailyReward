package me.dailyreward.Events;

import me.dailyreward.Configuration.ConfigPlayer;
import me.dailyreward.Configuration.Yaml;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.MongoDB.Profile;
import me.dailyreward.Databases.MySQL.PlayerMySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class JoinEvent implements Listener {

	private final DailyReward plugin;
	private Yaml playerConfig;

	public JoinEvent(DailyReward plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
		Player player = event.getPlayer();

		switch (this.plugin.getConfiguration().getString("DATABASE")) {
			case "MONGODB":
				this.plugin.getProfileManager().handleProfileCreation(player.getUniqueId(), player.getName());
				Profile profile = this.plugin.getProfileManager().getProfile(player.getUniqueId());

				profile.getPlayerData().getTime().setTime(0);

				profile.getPlayerData().save("DEFAULT");
				profile.getPlayerData().save("AMAZING");
				profile.getPlayerData().save("LION");
				profile.getPlayerData().save("CRUEL");
				break;
			case "MYSQL":
				PlayerMySQL playerData = new PlayerMySQL();
				playerData.insertPlayer(player);
				break;
			case "LOCAL":
				playerConfig = ConfigPlayer.getPlayer(player);

				playerConfig.add("DEFAULT", 0);
				playerConfig.add("AMAZING", 0);
				playerConfig.add("LION", 0);
				playerConfig.add("CRUEL", 0);

				playerConfig.save();
		}
	}
}
