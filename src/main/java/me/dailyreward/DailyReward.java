package me.dailyreward;

import lombok.Getter;
import lombok.SneakyThrows;
import me.dailyreward.Commands.CommandFramework;
import me.dailyreward.Commands.CommandList.Reward;
import me.dailyreward.Commands.CommandList.RewardReload;
import me.dailyreward.Commands.CommandList.RewardRemove;
import me.dailyreward.Configuration.Config;
import me.dailyreward.Databases.Database;
import me.dailyreward.Utils.Time;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public final class DailyReward extends JavaPlugin {

	@Getter
	private static DailyReward instance;
	private CommandFramework framework;
	private Config configuration;
	private Database database;
	private Time time;

	@SneakyThrows
	@Override
	public void onEnable() {
		/** Sets plugin to main class (this) **/
		instance = this;

		/** Load all local configurations **/
		configuration = new Config(this);
		configuration.loadConfiguration();

		/** Register events **/
		//new JoinEvent(instance);

		/** MongoDB **/
		System.setProperty("DEBUG.GO", "true");
		System.setProperty("DB.TRACE", "true");
		Logger mongoLobber = Logger.getLogger("org.mongodb.driver");
		mongoLobber.setLevel(Level.WARNING);

		/** Connection **/
		database = new Database();
		database.connect();

		time = new Time();

		/** Command framework implementation **/
		framework = new CommandFramework(this);
		framework.registerCommands(new Reward());
		framework.registerCommands(new RewardReload());
		framework.registerCommands(new RewardRemove());
		framework.registerHelp();
	}

	@Override
	public void onDisable() {
		database.disconnect();
	}

	public String getPrefix() {
		if (getConfiguration().getBoolean("ENABLE_PREFIX")) return getConfiguration().getString("PREFIX");
		return "";
	}
}
