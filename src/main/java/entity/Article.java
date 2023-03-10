package entity;

public class Article {
	private int id;
	private String nom;
	private double prix;
	private String categorie;
	private String race;
	private String description;
	private String image;

	public Article() {
	}

	public Article(String nom, double prix, String description, String categorie, String race, String image) {
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
		this.race = race;
		this.image = image;
	}

	public Article(int id, String nom, double prix, String categorie, String race, String description, String image) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
		this.race = race;
		this.description = description;
		this.image = image;
	}

	public final String getImage() {
		return image;
	}

	public final void setImage(String image) {
		this.image = image;
	}

	public final String getNom() {
		return nom;
	}

	public final void setNom(String nom) {
		this.nom = nom;
	}

	public final double getPrix() {
		return prix;
	}

	public final void setPrix(double prix) {
		this.prix = prix;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final String getCategorie() {
		return categorie;
	}

	public final void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getRace() {
		return race;
	}

	public final void setRace(String race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", nom=" + nom + ", prix=" + prix + ", description=" + description + ", categorie="
				+ categorie + ", race=" + race + ", image=" + image + "]";
	}

}
