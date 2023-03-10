package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Article;

public class ArticleDaoImpl implements ArticleDao {
	private Connection con;
	private List<Article> Articles;

	public ArticleDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public int insere(Article art) throws Exception{

			ResultSet result;
			PreparedStatement prep;
			String query = "insert into article (nom,prix,description,categorie) VALUES " + "(\"" + art.getNom()
					+ "\",\"" + art.getPrix() + "\",\"" + art.getDescription() + "\",\"" + art.getCategorie() + "\");";

			prep = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prep.execute();
			result = prep.getGeneratedKeys();

			if (result.next() && result != null) {
				art.setId(result.getInt(1));
			}

			Articles.add(art);
		return art.getId();
	}

	@Override
	public void supprime(Article art) throws Exception {
		Statement canal = con.createStatement();
		canal.execute("delete from article where id = " + art.getId() + ";");
		Articles.remove(art);
	}

	@Override
	public void update(Article art) throws Exception {
		Statement canal = con.createStatement();
		canal.execute("UPDATE article SET nom = \"" + art.getNom() + "\", prix = \"" + art.getPrix()
				+ "\", description = \"" + art.getDescription() + "\", categorie = \"" + art.getCategorie() + "\" "
				+ "WHERE Article.id = " + art.getId() + "; ");
	}

	@Override
	public Article trouve(int id) throws Exception {
		Article result = null;
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from article where id = " + id + ";");
		while (req.next()) {
			result = new Article(req.getInt("id"), req.getString("nom"), req.getDouble("prix"),
					req.getString("description"), req.getString("categorie"), req.getString("race"), req.getString("image"));
		}
		return result;
	}

	@Override
	public List<Article> trouveTout() throws Exception {
		List<Article> results = new ArrayList<Article>();
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from article where commande_id IS NULL;");
		while (req.next()) {
			results.add(new Article(req.getInt("id"), req.getString("nom"), req.getDouble("prix"),
					req.getString("description"), req.getString("categorie"), req.getString("race"), req.getString("image")));
		}
		return results;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		this.Articles = articles;
	}

}
