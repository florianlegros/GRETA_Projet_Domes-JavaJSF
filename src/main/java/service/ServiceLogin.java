package service;

import java.util.ArrayList;
import java.util.List;

import entity.User;

public class ServiceLogin {

	List<User> bdd = new ArrayList<User>();

	public ServiceLogin() {
		bdd.add(new User(1, "martin", "sesame"));
		bdd.add(new User(2, "raoul", "velo"));
		bdd.add(new User(2, "titi", "velo"));
		bdd.add(new User(2, "raoul", "pire"));
		bdd.add(new User(3, "titi", "abc"));
	}

	public boolean isValide(User l) {
		if (l.getUsername().matches("[a-zA-Z0-9]+") && l.getPassword().matches("[a-zA-Z0-9]+"))
			return true;
		return false;
	}

	public boolean existe(User l) {
		for (User e : bdd)
			if (e.getUsername().equals(l.getUsername()) && e.getPassword().equals(l.getPassword()))
				return true;
		return false;
	}

	public void create(User nouveauLogin) {
		bdd.add(nouveauLogin);
	}

	public void delete(User loginADetruire) {
		bdd.remove(loginADetruire);
	}

	public List<User> findAll() {
		return bdd;
	}


	public String[][] findAllAsString() {
		String[][] result = new String[bdd.size()][2];
		for(int i = 0; i < result.length; i++) {
			result[i][0] = bdd.get(i).getNom();
			result[i][1] = bdd.get(i).getPassword();
		}
		return result;
	}
}