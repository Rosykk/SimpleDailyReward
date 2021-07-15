package me.dailyreward.Utils;

import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;

public class DB {

    private static final DailyReward plugin = DailyReward.getInstance();
    private static final Config config = plugin.getCfg();

    public static boolean getMongo() {
        if(config.getConfigBool("DATABASE.MONGODB")) return true;
        return false;
    }

    public static boolean getMySQL() {
        if(config.getConfigBool("DATABASE.MYSQL")) return true;
        return false;
    }

    public static boolean getLocalFiles() {
        if(!config.getConfigBool("DATABASE.MONGODB") && !config.getConfigBool("DATABASE.MYSQL")) return true;
        return false;
    }

}
