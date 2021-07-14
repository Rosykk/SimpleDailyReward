package me.dailyreward.Configuration;

import me.dailyreward.DailyReward;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Config {

	private DailyReward plugin;

	public Config(DailyReward dailyreward) {
		this.plugin = dailyreward;
	}

	/* if config folder does not exist, create one and generate configs */
	public void loadConfiguration() {
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		createData();
		ConfigManager.setup();
	}

	public void createData() {
		File data = new File(plugin.getDataFolder(), "playerdata");
		if(!data.exists()) {
			data.mkdir();
		}
	}

	public FileConfiguration getConfig() {
		return ConfigManager.getConfig();
	}

	public ConfigurationSection getConfigSection(String s) {
		return getConfig().getConfigurationSection(s);
	}

	public String getConfigString(String s) {
		return getConfig().getString(s);
	}

	public Boolean getConfigBool(String s) {
		return getConfig().getBoolean(s);
	}
}
