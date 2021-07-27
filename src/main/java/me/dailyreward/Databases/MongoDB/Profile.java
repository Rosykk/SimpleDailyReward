package me.dailyreward.Databases.MongoDB;

import lombok.Getter;
import lombok.Setter;
import me.dailyreward.DailyReward;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private DailyReward plugin = DailyReward.getInstance();

    private PlayerData playerData;
    private UUID UUID;
    private String playerName;

    public Profile(UUID uuid, String name) {
        this.UUID = uuid;
        this.playerName = name;
        this.playerData = new PlayerData(uuid, name);
    }

}
