package me.dailyreward.Databases.MySQL;

import lombok.Getter;
import me.dailyreward.DailyReward;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class PlayerMySQL {

	private final DailyReward plugin = DailyReward.getInstance();

	public void dropTable() throws SQLException {
		PreparedStatement ps = plugin.getDb().getMySQL().getConnection().prepareStatement("DELETE * FROM `dailyreward`");
		ps.executeUpdate();
	}

	public void removePlayer(String string) throws SQLException {
		PreparedStatement ps = plugin.getDb().getMySQL().getConnection().prepareStatement("DELETE * FROM `dailyreward` WHERE player=?");
		ps.setString(1, string);
		ps.executeUpdate();
	}

	public boolean playerExists(String name) throws SQLException {
		PreparedStatement ps = plugin.getDb().getMySQL().getConnection().prepareStatement("SELECT * FROM `dailyreward` WHERE player=?");
		ps.setString(1, name);

		ResultSet rs = ps.executeQuery();

		int i = 0;
		while(rs.next()); { i++; }
		if(i == 0) { return false; }
		return true;
	}

	public void updateTime(String name, long time, Player player) throws SQLException {
		PreparedStatement ps = plugin.getDb().getMySQL().getConnection().prepareStatement("UPDATE `dailyreward` SET (?) = ? WHERE player=?");
		ps.setString(1, name);
		ps.setLong(2, time);
		ps.setString(3, player.getName());
		ps.executeUpdate();
	}

	public void insertPlayer(Player player) throws SQLException {
		PreparedStatement ps = plugin.getDb().getMySQL().getConnection().prepareStatement("SELECT * FROM `dailyreward` WHERE player=?");
		ps.setString(1, player.getName());

		ResultSet rs = ps.executeQuery();
		int i = 0;
		while(rs.next()) {
			i++;
		}

		if(i == 0) {
			ps = plugin.getDb().getMySQL().getConnection().prepareStatement("INSERT INTO `dailyreward` (`player`) VALUES (?)");
			ps.setString(1, player.getName());
			ps.executeUpdate();
		}
	}
}
