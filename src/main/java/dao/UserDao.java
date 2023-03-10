package dao;

import java.util.List;

import entity.User;

public interface UserDao {

	void insere(User log) throws Exception;

	void supprime(User log) throws Exception;

	User trouve(int id) throws Exception;

	List<User> trouveTout() throws Exception;

	boolean chercheLogin(String log, String pass) throws Exception;

	boolean chercheUsername(String username) throws Exception;
}
