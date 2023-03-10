package dao;

import java.util.List;

import entity.Article;

public interface ArticleDao {

	int insere(Article art) throws Exception;

	void supprime(Article art) throws Exception;

	void update(Article art) throws Exception;

	Article trouve(int id) throws Exception;

	List<Article> trouveTout() throws Exception;

}
