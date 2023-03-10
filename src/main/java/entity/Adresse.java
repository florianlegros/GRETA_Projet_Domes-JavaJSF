package entity;

import java.util.Objects;

public class Adresse {
	private int id;
	private String adresse;
	
	public Adresse() {
	}

	public Adresse(String adresse) {
		this.adresse = adresse;
	}

	public Adresse(int id, String adresse) {
		this.id = id;
		this.adresse = adresse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
	    return String.valueOf(id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		return id == other.id;
	}

}
