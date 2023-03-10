package dao;

import java.util.List;

import entity.Article;
import entity.Commande;

public interface CommandeDao {

	void insere(String username, String password, Article art, int adresse) throws Exception;

	List<Commande> getAllByUser(String username, String password) throws Exception;

}
