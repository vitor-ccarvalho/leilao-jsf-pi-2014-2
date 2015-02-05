package mBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.ItemModel;
import model.LeilaoModel;

@ManagedBean
@SessionScoped
public class ItemBean {
	private String productDescription;
	private double initialOffer;
//	private ItemModel itemModel;
	
	public ItemBean(){}
	
	public ItemBean(String productDescription, double initialOffer) {
		super();
		this.productDescription = productDescription;
		this.initialOffer = initialOffer;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getInitialOffer() {
		return initialOffer;
	}
	public void setInitialOffer(double initialOffer) {
		this.initialOffer = initialOffer;
	}
	
	public String salve(){
		LeilaoModel.getInstance().createLote(productDescription, initialOffer);
		return "leilao";
	}
	
//	public ItemModel getItemModel() {
//		return itemModel;
//	}
//	public void setItemModel(ItemModel itemModel) {
//		this.itemModel = itemModel;
//	}
}
