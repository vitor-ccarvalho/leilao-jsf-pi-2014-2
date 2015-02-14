/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import model.persistence.LeilaoDAO;

public class Leilao implements Serializable{

	private static final long serialVersionUID = 1L;
	private TreeMap<Key, ItemModel> items;
    private static Leilao INSTANCE;
    
    private Leilao() {
        items = new TreeMap<Key, ItemModel>();
    }
    
    public static Leilao getInstance(){
        if(INSTANCE == null) {
            if(!LeilaoDAO.checkFile()) {
                INSTANCE = new Leilao();
            } else {
                INSTANCE = (Leilao) LeilaoDAO.load();
            }
            return INSTANCE;
        }
        return INSTANCE;        
    }
    
    public ItemModel getItemModel(Key key) {
        return items.get(key);
    }
    
    public Collection<ItemModel> getItens(boolean isLock) {
        
        Collection<ItemModel> itens = new LinkedList<ItemModel>();
        for(Map.Entry<Key, ItemModel> entry : items.entrySet()) {
            ItemModel item = entry.getValue();
            if (item.isLock() == isLock) {
                itens.add(item);
            }
        }
        return itens;
    }
    
    public ItemModel createLote(String description, double initialValue) {
        
        Key key = this.nextKey();
        ItemModel lote = new ItemModel(key, description, initialValue);
        
        items.put(key, lote);
        
        LeilaoDAO.save(INSTANCE);
        
        return lote;
    }
    
    private Key nextKey(){
        
        Map.Entry<Key,ItemModel> lastEntry = items.lastEntry();
        
        int keyValue = lastEntry == null ? 1 : lastEntry.getKey().getValue() + 1;
        Key next = new Key(keyValue);
        
        return next;
    }
    
}
