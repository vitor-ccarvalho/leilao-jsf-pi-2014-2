package mBeans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Bid;
import model.ItemModel;
import model.LeilaoModel;

@ManagedBean
@SessionScoped
public class ItemBean {
	private String productDescription;
	private double initialOffer;
	private ItemModel itemModel;
	
	public ItemBean(){}
	
	public ItemBean(ItemModel itemModel) {
		super();
		this.productDescription = itemModel.getProductDescription();
		this.initialOffer = itemModel.getInitialValue();
		this.itemModel = itemModel;  
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
	
	public String bestOffer(){
		return itemModel.getBestValue()+"";
	}
	
	public String bestNameOffer(){
		if (itemModel.getWinnerBid() != null) { 
			return itemModel.getWinnerBid().getPerson();
		} else { // se não houve nenhum lance para o item em leilão, então temos que tratar, retornando o valor de inicial
			return "Sem lances"; 
		}
	}
	
	public String save(){
		itemModel = LeilaoModel.getInstance().createLote(productDescription, initialOffer);
		return "leilao";
	}
	
	public ItemModel getItemModel() {
		return itemModel;
	}
	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}
	public void editItemModel(ItemModel itemModel) {
		setItemModel(itemModel);
	}
	
	public String darLance(){
		return "darLance";
	}
	
	public String close(){
		itemModel.setLock(true);
		return "leilao";
	}
	
	public Collection<BidBean> bids(){
		Collection<BidBean> bids = new LinkedList<BidBean>();
		for(Bid bidModel : itemModel.getBids()) {
			bids.add(convert(bidModel));
		}
		return bids; 
	}
	
	private BidBean convert(Bid bid) {
		BidBean bidBean = new BidBean();
		bidBean.setPerson(bid.getPerson());
		bidBean.setValue(bid.getValue());
		bidBean.setItemModel(itemModel);
		return bidBean;
	}
}
