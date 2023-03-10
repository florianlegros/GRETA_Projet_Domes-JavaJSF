package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FavorisDaoImpl implements FavorisDao {
	private Connection con;

	public FavorisDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public void favoris(String username, String password, int id) throws Exception {
		Statement canal = con.createStatement();
		canal.execute(
				"INSERT INTO `favoris`(`user_id`, `article_id`) VALUES ( (SELECT `id` FROM `user` WHERE `username` = '"
						+ username + "' AND `password` = '" + password + "') ," + id + ");");
	}

	@Override
	public void defavoris(String username, String password, int id) throws Exception {
		Statement canal = con.createStatement();
		canal.execute("DELETE FROM `favoris` WHERE `user_id`= (SELECT `id` FROM `user` WHERE `username` = '" + username
				+ "' AND `password` = '" + password + "') AND `article_id`=" + id + ";");
	}

	@Override
	public boolean verifFav(String username, String password, int id) throws Exception {
		Statement canal = con.createStatement();
		String req = "select * from favoris where `user_id`= (SELECT `id` FROM `user` WHERE `username` = '" + username
				+ "' AND `password` = '" + password + "') AND `article_id`=" + id + ";";
		ResultSet res = canal.executeQuery(req);
		return res.next();
	}

}