package me.dailyreward.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public final class Color {

	public static String colorize(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static void sendMessage(String s, CommandSender sender) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
	}
}
