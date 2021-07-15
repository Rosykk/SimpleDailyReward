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

	private DailyReward plugin;
	private Yaml config;

	private PlayerMySQL playerData;

	public JoinEvent(DailyReward plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	/** Local files **/
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if(!plugin.getCfg().getConfigBool("DATABASE.MONGODB") && !plugin.getCfg().getConfigBool("DATABASE.MYSQL")) {
			/* If configuration for player doesn't exist, generate */
			config = ConfigPlayer.getPlayer(player);

			config.add("DEFAULT", 0);
			config.add("AMAZING", 0);
			config.add("LION", 0);
			config.add("CRUEL", 0);

			/* Save player data */
			config.save();
		}
	}

	/** MongoDB **/
	@EventHandler
	public void onJoinMongo(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if(plugin.getCfg().getConfigBool("DATABASE.MONGODB")) {

		}
	}


	/** MySQL **/
	@EventHandler
	public void onJoinMySQL(PlayerJoinEvent event) throws SQLException {
		Player player = event.getPlayer();

		if(plugin.getCfg().getConfigBool("DATABASE.MYSQL")) {
			playerData = new PlayerMySQL();

			playerData.insertPlayer(player);
		}
	}
}
