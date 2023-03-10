package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Article;

@Named
@SessionScoped
public class CatalogueBrowserBean implements Serializable {

	private static final long serialVersionUID = -5433850275008415405L;

	@Inject
	private Catalogue catalogue;
	private int index;

	private List<Article> cart = new ArrayList<>();


	public Article getCurrentArticle() {
		return catalogue.getArticles().get(index);
	}

	public List<Article> getCart() {
		return cart;
	}

	public void setCart(List<Article> cart) {
		this.cart = cart;
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void ProcessAddAction(ActionEvent event) {
		HtmlCommandButton btn = (HtmlCommandButton) event.getSource();

		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getId() == Integer.parseInt(btn.getLabel())) {
				cart.remove(i);
			}
		}

		for (int i = 0; i < catalogue.getArticles().size(); i++) {
			if (catalogue.getArticles().get(i).getId() == Integer.parseInt(btn.getLabel())) {
				cart.add(catalogue.getArticles().get(i));
			}
		}

	}



	public void ProcessDelAction(ActionEvent event) {
		HtmlCommandButton btn = (HtmlCommandButton) event.getSource();

		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getId() == Integer.parseInt(btn.getLabel())) {
				cart.remove(i);
			}
		}

	}

	public void ProcessPreviousAction(ActionEvent event) {
		if (index > 0)
			index--;
	}

	public void ProcessNextAction(ActionEvent event) {
		if (index < catalogue.getSize() - 1)
			index++;
	}
}