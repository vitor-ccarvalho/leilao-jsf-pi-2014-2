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

/**
 *
 * @author Guilherme
 */
public class Leilao implements Serializable{

    private TreeMap<Key, Item> items;
    private static Leilao INSTANCE;
    
    private Leilao() {
        items = new TreeMap<Key, Item>();
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
    
    public Item getItem(Key key) {
        return items.get(key);
    }
    
    public Collection<Item> getItens(boolean isLock) {
        
        Collection<Item> itens = new LinkedList<Item>();
        for(Map.Entry<Key, Item> entry : items.entrySet()) {
            Item item = entry.getValue();
            if (item.isLock() == isLock) {
                itens.add(item);
            }
        }
        return itens;
    }
    
    public Item createLote(String description, double initialValue) {
        
        Key key = this.nextKey();
        Item lote = new Item(key, description, initialValue);
        
        items.put(key, lote);
        
        LeilaoDAO.save(INSTANCE);
        
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
        
        Map.Entry<Key,Item> lastEntry = items.lastEntry();
        
        int keyValue = lastEntry == null ? 1 : lastEntry.getKey().getValue() + 1;
        Key next = new Key(keyValue);
        
        return next;
    }
    
}
