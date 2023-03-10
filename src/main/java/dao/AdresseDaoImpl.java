package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Adresse;

public class AdresseDaoImpl implements AdresseDao {
	private Connection con;

	public AdresseDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public void insere(String username, String password, String adresse) throws Exception {
		Statement canal = con.createStatement();
		String query = "INSERT INTO `adresse`(`adresse`,  `user_id`) VALUES " + "('" + adresse
				+ "',(SELECT `id` FROM `user` WHERE `username` = '" + username + "' AND `password` = '" + password
				+ "'));";
		canal.execute(query);
	}

	@Override
	public void update(String username, String password, String adresse) throws Exception {
		Statement canal = con.createStatement();
		canal.execute("UPDATE adresse SET adresse = \"" + adresse
				+ "WHERE User.id = (SELECT `id` FROM `user` WHERE `username` = '" + username + "' AND `password` = '"
				+ password + "');");
	}

	@Override
	public List<Adresse> getAllByUser(String username, String password) throws Exception {

		List<Adresse> results = new ArrayList<Adresse>();
		Statement canal = con.createStatement();
		ResultSet req = canal
				.executeQuery("select * from adresse WHERE user_id= (SELECT `id` FROM `user` WHERE `username` = '"
						+ username + "' AND `password` = '" + password + "')" + ";");
		while (req.next()) {
			results.add(new Adresse(req.getInt("id"),req.getString("adresse")));
		}

		return results;
	}

}
