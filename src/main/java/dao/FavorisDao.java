package dao;

public interface FavorisDao {

	void favoris(String username, String password, int id) throws Exception;

	void defavoris(String username, String password, int id) throws Exception;

	boolean verifFav(String username, String password, int id) throws Exception;

}