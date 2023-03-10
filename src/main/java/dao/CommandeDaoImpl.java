package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Article;
import entity.Commande;

public class CommandeDaoImpl implements CommandeDao {
	private Connection con;

	public CommandeDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public void insere(String username, String password, Article art, int adresse) {
		// insert intto ..

		try {
			ResultSet result;
			PreparedStatement prep;
			int id_commande;
			String query = "INSERT INTO `commande`(`date`,  `user_id`,`adresse_id`) VALUES "
					+ "(NOW(),(SELECT `id` FROM `user` WHERE `username` = '" + username + "' AND `password` = '"
					+ password + "') ," + adresse + ");";

			prep = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prep.execute();
			result = prep.getGeneratedKeys();

			if (result.next() && result != null) {
				id_commande = result.getInt(1);

				Statement canal = con.createStatement();
				canal.execute("UPDATE article SET id_commande = " + id_commande + " WHERE id = " + art.getId() + "; ");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Commande> getAllByUser(String username, String password) throws Exception {

		List<Commande> results = new ArrayList<Commande>();
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery(
				"select * from commande JOIN article ON commande.id = article.commande_id JOIN adresse ON commande.adresse_id = adresse.id WHERE commande.user_id= (SELECT `id` FROM `user` WHERE `username` = '"
						+ username + "' AND `password` = '" + password + "')" + " ORDER BY date DESC;");
		while (req.next()) {
			results.add(new Commande(req.getInt("commande.id"), req.getString("date"), req.getString("adresse"),req.getString("status"),
					new Article(req.getInt("article.id"), req.getString("nom"), req.getDouble("prix"),
							req.getString("description"), req.getString("categorie"), req.getString("race"),
							req.getString("image"))));
		}

		return results;
	}

}
