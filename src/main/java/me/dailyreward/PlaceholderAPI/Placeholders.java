package me.dailyreward.PlaceholderAPI;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.dailyreward.DailyReward;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholders extends PlaceholderExpansion {

    private final DailyReward plugin = DailyReward.getInstance();

    @Override
    public @NotNull String getIdentifier() {
        return "sdr";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Rosykk";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.2";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {

        if (player == null) return "";

        if (params.equals("remaining_time")) {
            return String.valueOf(1);
        }

        return null;
    }
}
