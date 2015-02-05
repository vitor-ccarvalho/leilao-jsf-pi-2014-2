package mBeans;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.ItemModel;
import model.LeilaoModel;

@ManagedBean
@ApplicationScoped
public class LeiloesBean {
    
    public Collection<ItemBean> abertos() {
        Collection<ItemModel> itens = LeilaoModel.getInstance().getItems(false);
        
        return convertList(itens);
    }
    
    public Collection<ItemBean> fechados() {
    	Collection<ItemModel> itens = LeilaoModel.getInstance().getItems(true);
        
        return convertList(itens);
    }
    
    public Collection<ItemBean> convertList(Collection<ItemModel> itens){
    	Collection<ItemBean> itensBeans = new LinkedList<ItemBean>();
        
        for(ItemModel itemModel : itens) {
        	ItemBean item = new ItemBean(itemModel.getProductDescription(), itemModel.getInitialValue());
        	itensBeans.add(item);
        }
        
        return itensBeans;
    }
}
