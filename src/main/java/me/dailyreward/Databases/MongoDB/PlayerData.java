package me.dailyreward.Databases.MongoDB;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import me.dailyreward.DailyReward;
import org.bson.Document;

import java.util.UUID;

@Getter
public class PlayerData {

	private DailyReward plugin = DailyReward.getInstance();
	private MongoDB mongoDB = new MongoDB();

	private UUID UUID;
	private String playerName;

	private Stats time = new Stats();

	public PlayerData(UUID uuid, String playerName) {
		this.UUID = uuid;
		this.playerName = playerName;
	}

	public void resetData() {
		this.time.setTime(0);
	}

	public void load() {
		Document document = mongoDB.getMongoCollection().find(Filters.eq("uuid", getUUID().toString())).first();

		if(document != null) {
			this.time.setTime(document.getInteger("rewardTime"));
		}
	}

	public void save() {
		Document document = new Document();
		document.put("name", getPlayerName().toLowerCase());
		document.put("realName", getPlayerName());
		document.put("uuid", getUUID().toString());
		document.put("rewardTime", this.time.getAmount());
		mongoDB.getMongoCollection().replaceOne(Filters.eq("uuid", getUUID().toString()), document, new UpdateOptions());
	}

}
