package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDaoImpl implements UserDao {
	private Connection con;

	public UserDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public void insere(User log) throws Exception {
		Statement canal = con.createStatement();
		canal.execute("INSERT INTO user (`username`,`password`,`email`) VALUES ('" + log.getUsername() + "','"
				+ log.getPassword() + "','" + log.getEmail() + "');");
	}

	@Override
	public void supprime(User log) throws Exception {
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("delete * from user where id = " + log.getId() + ";");
	}

	@Override
	public User trouve(int id) throws Exception {
		User result = null;
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from user where id = " + id + ";");
		while (req.next()) {
			result = new User(req.getInt("id"), req.getString("username"), req.getString("password"));
		}
		return result;
	}

	@Override
	public List<User> trouveTout() throws Exception {
		List<User> results = new ArrayList<User>();
		Statement canal = con.createStatement();
		ResultSet req = canal.executeQuery("select * from user" + ";");
		while (req.next()) {
			results.add(new User(req.getInt("id"), req.getString("username"), req.getString("password")));
		}
		return results;
	}

	@Override
	public boolean chercheLogin(String log, String pass) throws Exception {

		Statement canal = con.createStatement();
		String req = "select * from user where username = '" + log + "' and password='" + pass + "'    ";
		ResultSet res = canal.executeQuery(req);
		return res.next();

	}

	@Override
	public boolean chercheUsername(String username) throws Exception {

		Statement canal = con.createStatement();
		String req = "select * from user where username = '" + username + "'";
		ResultSet res = canal.executeQuery(req);
		return res.next();

	}
}
