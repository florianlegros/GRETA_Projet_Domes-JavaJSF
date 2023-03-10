package dao;

import java.util.List;

import entity.Adresse;

public interface AdresseDao {

	void insere(String username, String password, String adresse) throws Exception;

	void update(String username, String password, String adresse) throws Exception;

	List<Adresse> getAllByUser(String username, String password) throws Exception;

}
