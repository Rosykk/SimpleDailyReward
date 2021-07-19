package me.dailyreward.Databases.MongoDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import me.dailyreward.DailyReward;
import me.dailyreward.Databases.Database;
import me.dailyreward.Utils.Color;
import org.bson.Document;

@Getter
public class MongoDB {

    private final DailyReward plugin = DailyReward.getInstance();
    private final Database db = new Database();
    /* Connection to MongoDB */
    private final boolean enabled = this.plugin.getCfg().getBoolean("DATABASE.MONGODB");
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;

    public void connect() {
        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://" + this.db.getUsername() + ":"
                            + this.db.getPassword() + "@" + this.db.getHost() + ":"
                            + this.db.getPort() + "/?authSource=" + db.getAuthDatabase());

            mongoClient = new MongoClient(uri);
            mongoDatabase = mongoClient.getDatabase(db.getAuthDatabase());
            mongoCollection = mongoDatabase.getCollection("Server");

            this.plugin.getLogger().info(Color.colorize("&aSuccessfully connected to MongoDB"));
        } catch (Exception e) {
            this.plugin.getLogger().info(Color.colorize("&cThere was an fucking error connecting to MongoDB!"));
            e.printStackTrace();
        }
    }

    public void disconnect() {
        mongoClient.close();
        plugin.getLogger().info(Color.colorize(db.getCloseDatabase()));
    }
}
