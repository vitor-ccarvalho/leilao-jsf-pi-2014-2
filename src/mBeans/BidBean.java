package mBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.ItemModel;

@ManagedBean
@SessionScoped
public class BidBean {
	
	private String person;
	private double value;
	private ItemModel itemModel;
	
	public ItemModel getItemModel() {
		return itemModel;
	}
	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}
	public void editItemModel(ItemModel itemModel) {
		setItemModel(itemModel);
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public String darLance(){
		itemModel.registry(person, value);
		return "leilao";
	}
}
