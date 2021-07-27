package me.dailyreward.Databases.MongoDB;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import me.dailyreward.DailyReward;
import org.bson.Document;

import java.util.UUID;

@Getter
public class PlayerData {

	private final DailyReward plugin = DailyReward.getInstance();
	private final MongoDB mongoDB = new MongoDB();

	private final UUID UUID;
	private final String playerName;

	private final Stats time = new Stats();

	public PlayerData(UUID uuid, String playerName) {
		this.UUID = uuid;
		this.playerName = playerName;
	}

	public void resetData() {
		this.time.setTime(0);
	}

	public void load(String string) {
		Document document = mongoDB.getMongoCollection().find(Filters.eq("uuid", getUUID().toString())).first();

		if (document != null) {
			this.time.setTime(document.getInteger(string));
		}
	}

	public void save(String string) {
		Document document = new Document();
		document.put("name", getPlayerName().toLowerCase());
		document.put("realName", getPlayerName());
		document.put("uuid", getUUID().toString());
		document.put(string, this.time.getAmount());
		mongoDB.getMongoCollection().replaceOne(Filters.eq("uuid", getUUID().toString()), document, new UpdateOptions().upsert(true));
	}

}
