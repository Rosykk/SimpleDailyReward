package me.dailyreward.Utils;

import me.dailyreward.Commands.CommandArgs;

public class Util {

    public static boolean argLength(CommandArgs args, int max) {
        return args.length() >= max;
    }

    public static boolean argLength(CommandArgs args, int min, int max) {
        return (args.length() >= max && args.length() <= min);
    }
}
