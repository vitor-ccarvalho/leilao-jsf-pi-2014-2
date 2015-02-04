/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import model.Leilao.Key;
import java.util.LinkedList;
import java.util.List;
import model.persistence.LeilaoDAO;

/**
 *
 * @author Guilherme
 */
public class Item implements Serializable{

    private Key key;
    private String productDescription;
    private double bestValue;    
    private boolean lock;
    private Bid winnerBid;
    private List<Bid> bids;
    private double initialValue;

    public Item(Key key, String description, double initialValue) {
        this.key = key;
        productDescription = description;
        bestValue = initialValue;
        this.initialValue = initialValue;
        lock = false;
        bids = new LinkedList<Bid>();
    }

    public void registry(String name, Double value) {
        
        if (!lock) {

            Bid bid = new Bid(name, value);

            if (winnerBid == null || bid.value > bestValue) {
                bids.add(bid);
                winnerBid = bid;
                bestValue = winnerBid.value;
                LeilaoDAO.save(Leilao.getInstance());
            }
        }
    }

    public Bid getWinner() {
        return winnerBid;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
        
        LeilaoDAO.save(Leilao.getInstance());
    }

    public class Bid implements Serializable {

        private String person;
        private Double value;

        public Bid(String person, Double value) {

            if (person != null && !person.isEmpty() && value != null && value > 0) {

                this.person = person;
                this.value = value;
            }
        }

        public String getPerson() {
            return person;
        }

        public Double getValue() {
            return value;
        }
               
    }

    public Key getKey() {
        return key;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getBestValue() {
        return bestValue;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public double getInitialValue() {
        return initialValue;
    }
}
