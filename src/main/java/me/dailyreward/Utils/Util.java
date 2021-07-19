package me.dailyreward.Utils;

import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.ConfigManager;

public class Util {

    public static boolean argLength(CommandArgs args, String configName, int max) {
        if (args.length() >= max) {
            Color.sendMessage(ConfigManager.getConfig().getString("MESSAGE_WRONG_ARGS"), args.getPlayer());
            return true;
        }
        return false;
    }

    public static boolean argLength(CommandArgs args, String configValue, int mix, int max) {
        if (args.length() >= max) {
            Color.sendMessage(ConfigManager.getConfig().getString("MESSAGE_WRONG_ARGS"), args.getPlayer());
            return true;
        }
        return false;
    }
}
