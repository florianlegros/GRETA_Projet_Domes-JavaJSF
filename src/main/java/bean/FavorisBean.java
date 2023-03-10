package bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import dao.ConnectBd;
import dao.FavorisDao;
import dao.FavorisDaoImpl;

@Named
@SessionScoped
public class FavorisBean implements Serializable {

	private static final long serialVersionUID = -5433850275008415405L;
	@Inject
	private LoginBean loginBean;
	private FavorisDao favd;

	public FavorisBean() {
		ConnectBd bd = new ConnectBd();
		bd.connect();
		favd = new FavorisDaoImpl(bd.getCon());
	}

	public void ProcessFavAction(ActionEvent event) {
		HtmlCommandButton btn = (HtmlCommandButton) event.getSource();

		if (VerifFav(Integer.parseInt(btn.getLabel())))
			defavoris(Integer.parseInt(btn.getLabel()));
		else
			favoris(Integer.parseInt(btn.getLabel()));

	}

	public boolean VerifFav(int id) {
		return verifFav(id);

	}

	public void favoris(int id) {
		try {
			favd.favoris(loginBean.getUsername(), loginBean.getPassword(), id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void defavoris(int id) {
		try {
			favd.defavoris(loginBean.getUsername(), loginBean.getPassword(), id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verifFav(int id) {
		try {
			return favd.verifFav(loginBean.getUsername(), loginBean.getPassword(), id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}