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

import javax.faces.bean.ManagedBean;

import model.persistence.LeilaoDAO;

/**
 *
 * @author Guilherme
 */
public class LeilaoModel implements Serializable{

    private TreeMap<Key, ItemModel> items;
    private static LeilaoModel INSTANCE;
    
    private LeilaoModel() {
    	//
    	Key key = new Key(2);
    	ItemModel item = new ItemModel(key, "bla", 10);
    	
    	
        items = new TreeMap<Key, ItemModel>();
        
        items.put(key, item);
    }
    
    public static LeilaoModel getInstance(){
        if(INSTANCE == null) {
//            if(!LeilaoDAO.checkFile()) {
                INSTANCE = new LeilaoModel();
//            } else {
//                INSTANCE = (LeilaoModel) LeilaoDAO.load();
//            }
            return INSTANCE;
        }
        return INSTANCE;        
    }
    
    public ItemModel getItem(Key key) {
        return items.get(key);
    }
    
    public Collection<ItemModel> getItems(boolean isLock) {
        
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
        
//        LeilaoDAO.save(INSTANCE);
        
        return lote;
    }
    
    public static class Key implements Comparable, Serializable {
        private Integer value;

        public Key(int value) {
            this.value = value;
        }
        
        public Integer getValue() {
            return value;
        }

        @Override
        public int compareTo(Object o) {
            if (o == null || !(o instanceof Key)) {
                return 1;
            }
            
            Key other = (Key) o;
            
            if (value < other.value) {
                return -1;
            } else if (value == other.value) {
                return 0;
            }
            
            return 1;
        }
        
        @Override
        public String toString() {
            return value.toString();
        }
    }
    
    private Key nextKey(){
        
        Map.Entry<Key,ItemModel> lastEntry = items.lastEntry();
        
        int keyValue = lastEntry == null ? 1 : lastEntry.getKey().getValue() + 1;
        Key next = new Key(keyValue);
        
        return next;
    }
    
}
