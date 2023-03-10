package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import dao.CommandeDao;
import dao.CommandeDaoImpl;
import dao.ConnectBd;
import entity.Adresse;
import entity.Article;
import entity.Commande;

@Named
@SessionScoped
public class CommandeBrowserBean implements Serializable {

	private static final long serialVersionUID = -5433850275008415405L;
	private CommandeDao cmdd;
	private List<Commande> history = new ArrayList<>();
	private Adresse adresse;
	@Inject
	private LoginBean loginBean;
	@Inject
	private CatalogueBrowserBean catalogueBrowserBean;

	public CommandeBrowserBean() {
		ConnectBd bd = new ConnectBd();
		bd.connect();
		cmdd = new CommandeDaoImpl(bd.getCon());
	}

	public CommandeDao getCmdd() {
		return cmdd;
	}

	public void setCmdd(CommandeDao cmdd) {
		this.cmdd = cmdd;
	}

	public List<Commande> getHistory() {
		try {
			history = cmdd.getAllByUser(loginBean.getUsername(), loginBean.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return history;
	}

	public void setHistory(List<Commande> history) {
		this.history = history;
	}

	public String Commander() {
		

		if (loginBean.getUsername().isBlank() || loginBean.getPassword().isBlank()) {
			return "login.xhtml";
		} 
		
		if (adresse == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choisir une adresse", null));
			return null;
		}
		
		else {
			for (Article art : catalogueBrowserBean.getCart()) {
				try {
					cmdd.insere(loginBean.getUsername(), loginBean.getPassword(), art, adresse.getId());
					catalogueBrowserBean.setCart(new ArrayList<>());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return "compte.xhtml";

	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}