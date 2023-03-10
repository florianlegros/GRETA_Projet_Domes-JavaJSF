package bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import dao.ArticleDao;
import dao.ArticleDaoImpl;
import dao.ConnectBd;
import entity.Article;

@Named
@ApplicationScoped
public class Catalogue {

	private List<Article> articles = new ArrayList<>();
	private ArticleDao artd;


	public Catalogue() {

		ConnectBd bd = new ConnectBd();
		bd.connect();
		artd = new ArticleDaoImpl(bd.getCon());
	}

	public int getSize() {
		return articles.size();
	}

	public List<Article> getArticles() {
		try {
			articles = artd.trouveTout();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



}
