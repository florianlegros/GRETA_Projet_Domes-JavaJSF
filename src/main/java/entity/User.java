package entity;

public class User {
	private int id;
	private String username;
	private String password;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String adresse;

	public User() {
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String username, String password, String nom, String prenom, String telephone, String email,
			String adresse) {
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.adresse = adresse;
	}

	public User(int id, String username, String password, String nom, String prenom, String telephone, String email,
			String adresse) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.adresse = adresse;
	}

	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getUsername() {
		return username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getNom() {
		return nom;
	}

	public final void setNom(String nom) {
		this.nom = nom;
	}

	public final String getPrenom() {
		return prenom;
	}

	public final void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public final String getTelephone() {
		return telephone;
	}

	public final void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getAdresse() {
		return adresse;
	}

	public final void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "username [id=" + id + ", username=" + username + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", telephone=" + telephone + ", email=" + email + ", adresse=" + adresse + "]";
	}

}
