package bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import dao.ConnectBd;
import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import service.ServiceLogin;

@Named
@SessionScoped
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = -5433850275008415405L;
	private UserDao userd;
	private String username = "";
	private String password = "";
	private String email = "";

	public RegisterBean() {
		ConnectBd bd = new ConnectBd();
		bd.connect();
		userd = new UserDaoImpl(bd.getCon());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String Enregistrer() {
		ServiceLogin service = new ServiceLogin();
		User newuser = new User(username, password, email);
		if (username.isBlank() || password.isBlank() || email.isBlank()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Un ou plusieurs champ(s) vides", null));
			return null;
		}

		if (!service.isValide(newuser)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caractere interdit dans l'un des champs", null));
			return null;
		}
		try {
			if (userd.chercheUsername(username)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Un compte existe deja avec ce nom d'utilisateur", null));
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			userd.insere(newuser);
			return "login.xhtml";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Une erreur est survenu", null));
		return null;

	}

}