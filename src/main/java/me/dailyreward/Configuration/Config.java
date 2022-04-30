package me.dailyreward.Configuration;

import me.dailyreward.DailyReward;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

public class Config {

    private final DailyReward plugin;

    public Config(DailyReward dailyreward) {
        this.plugin = dailyreward;
    }

    /* if config folder does not exist, create one and generate configs */
    public void loadConfiguration() {
        createFolder();
        createData();
        ConfigManager.setup();
    }

    public void createFolder() throws SecurityException {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
    }

    public void createData() {
        File data = new File(plugin.getDataFolder(), "playerdata");

        if ( !data.exists() ) {
            data.mkdir();
        }
    }

    public FileConfiguration getConfig() {
        return ConfigManager.getConfig();
    }

    public String getString(String s) {
        return getConfig().getString(s);
    }

    public long getLong(String s) { return getConfig().getLong(s); }

    public Boolean getBoolean(String s) {
        return getConfig().getBoolean(s);
    }

    public List<String> getStringList(String s) {
        return getConfig().getStringList(s);
    }

    public @NotNull ConfigurationSection getSection(String s) { return getConfig().getDefaultSection(); }
}
