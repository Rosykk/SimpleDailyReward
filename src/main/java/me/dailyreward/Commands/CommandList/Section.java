package me.dailyreward.Commands.CommandList;

import me.dailyreward.Commands.BaseCommand;
import me.dailyreward.Commands.Command;
import me.dailyreward.Commands.CommandArgs;
import me.dailyreward.Configuration.Config;
import me.dailyreward.DailyReward;
import org.bukkit.entity.Player;

public class Section extends BaseCommand {

    private final DailyReward plugin = DailyReward.getInstance();

    @Override
    @Command(name = "section")
    public void onCommand(CommandArgs args) {
        Player player = args.getPlayer();

        Config config = this.plugin.getConfiguration();

        for (String string : config.getSection("REWARDS").getKeys(false) ) {
            player.sendMessage(string);
        }
    }
}
