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
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -5433850275008415405L;
	private UserDao userd;
	private String username = "";
	private String password = "";

	public LoginBean() {
		ConnectBd bd = new ConnectBd();
		bd.connect();
		userd = new UserDaoImpl(bd.getCon());

	}

	public String onLoad() {
		if (!username.isBlank() || !password.isBlank()) {
			return connect();
		}
		return "login.xhtml";
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

	public String deconnect() {
		username = "";
		password = "";
		return "index.xhtml";
	}

	public String connect() {
		ServiceLogin service = new ServiceLogin();
		User con = new User(username, password, "");

		if (username.isBlank() || password.isBlank()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Un ou plusieurs champ(s) vides", null));
			return null;
		}

		if (!service.isValide(con)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caractere interdit dans l'un des champs", null));
			return null;
		}

		try {
			if (userd.chercheLogin(username, password)) {

				return "compte.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucun compte ne corresponds a ces identifiants", null));
		return null;

	}

}