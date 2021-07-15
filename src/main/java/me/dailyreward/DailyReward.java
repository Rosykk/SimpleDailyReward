package me.dailyreward;

import lombok.Getter;
import lombok.SneakyThrows;
import me.dailyreward.Commands.CommandFramework;
import me.dailyreward.Commands.CommandList.*;
import me.dailyreward.Configuration.Config;
import me.dailyreward.Databases.Database;
import me.dailyreward.Events.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public final class DailyReward extends JavaPlugin {



	@Getter
	private static DailyReward instance;
	private CommandFramework framework;
	private Config cfg;
	private Database db;

	@SneakyThrows
	@Override
	public void onEnable() {
		/** Sets plugin to main class (this) **/
		instance = this;

		/** Load all local configurations **/
		cfg = new Config(this);
		cfg.loadConfiguration();

		/** Register events **/
		new JoinEvent(instance);

		/** MongoDB **/
		/* Cleaner debug */
		System.setProperty("DEBUG.GO", "true");
		System.setProperty("DB.TRACE", "true");
		Logger mongoLobber = Logger.getLogger("org.mongodb.driver");
		mongoLobber.setLevel(Level.WARNING);

		/** Connection **/
		db = new Database();
		db.connect();

		/** Connection MySQL **/

		/** Command framework implementation **/
		framework = new CommandFramework(this);
		framework.registerCommands(new Reward());
		framework.registerCommands(new RewardAmazing());
		framework.registerCommands(new RewardLion());
		framework.registerCommands(new RewardCruel());
		framework.registerCommands(new RewardReload());
		framework.registerHelp();
	}

	@Override
	public void onDisable() {
		db.getMySQL().disconnect();
	}

	public String getPrefix() {
		if(getCfg().getConfigBool("ENABLE_PREFIX")) return getCfg().getConfigString("PREFIX");
		return "";
	}
}
