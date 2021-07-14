package me.dailyreward.Configuration;

import me.dailyreward.DailyReward;
import me.dailyreward.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

	private static DailyReward plugin = DailyReward.getInstance();

	private static FileConfiguration CONFIG_FILE;
	private static File CONFIG;

	public static void setup() {
		// if config.yml does not exit, create one
		CONFIG = new File(plugin.getDataFolder(), "config.yml");
		if(!CONFIG.exists()) {
			try {
				plugin.saveResource("config.yml", false);
				Bukkit.getLogger().info(Color.colorize("&aconfig.yml has been created!"));
			} catch (Exception e) {
				Bukkit.getLogger().info(Color.colorize("&ccould not create config.yml!"));
			}
		}

		CONFIG_FILE = YamlConfiguration.loadConfiguration(CONFIG);
	}

	/* reload config.yml config */
	public static void reloadConfig() {
		CONFIG_FILE = YamlConfiguration.loadConfiguration(CONFIG);
	}

	/* get config.yml config */
	public static FileConfiguration getConfig() {
		return CONFIG_FILE;
	}
}
