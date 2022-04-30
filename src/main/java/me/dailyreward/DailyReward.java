package me.dailyreward;

import lombok.Getter;
import lombok.SneakyThrows;
import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.CommandFramework;
import me.dailyreward.Commands.CommandList.*;
import me.dailyreward.Configuration.Config;
import me.dailyreward.Databases.Database;
import me.dailyreward.Events.JoinEvent;
import me.dailyreward.PlaceholderAPI.Placeholders;
import me.dailyreward.Utils.Time;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
		/* Sets plugin to main class (this) */
		instance = this;

		/*  Load all local configurations */
		configuration = new Config(this);
		configuration.loadConfiguration();

		/*  Register events */
		new JoinEvent(instance);

		/*  Connection */
		database = new Database();
		database.connect();

		time = new Time();

		/*  Command framework implementation */
		framework = new CommandFramework(this);

		registerAll(
				new Reward(), new RewardRemove(), new RewardReload(),
				new RewardPurge(), new Section()
		);

		framework.registerHelp();

		/* PlaceholderAPI */
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			new Placeholders().register();
		}
	}

	@Override
	public void onDisable() { database.disconnect(); }

	/* -------------------- Custom methods -------------------- */

	private void registerAll(BaseCommand ... framework) {
		getFramework().registerCommands(framework);
	}

	public String getPrefix() {
		return ( configuration.getBoolean("ENABLE_PREFIX") ) ? configuration.getString("PREFIX") : "";
	}

	/* -------------------------------------------------------- */
}
