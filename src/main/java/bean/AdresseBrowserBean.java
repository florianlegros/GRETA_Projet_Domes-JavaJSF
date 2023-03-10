package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.AdresseDao;
import dao.AdresseDaoImpl;
import dao.ConnectBd;
import entity.Adresse;

@Named
@SessionScoped
public class AdresseBrowserBean implements Serializable {

	private static final long serialVersionUID = -5433850275008415405L;
	private AdresseDao addr;
	private String adresse;
	private List<Adresse> adresses = new ArrayList<>();
	@Inject
	private LoginBean loginBean;

	public AdresseBrowserBean() {
		ConnectBd bd = new ConnectBd();
		bd.connect();
		addr = new AdresseDaoImpl(bd.getCon());
	}

	public AdresseDao getAddr() {
		return addr;
	}

	public void setAddr(AdresseDao addr) {
		this.addr = addr;
	}
	public void ajouterAdresse() {
		try {
			addr.insere(loginBean.getUsername(), loginBean.getPassword(),adresse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List<Adresse> getAdresses() {
		try {
			adresses = addr.getAllByUser(loginBean.getUsername(), loginBean.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

}