/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Key key;
    private String productDescription;
    private double bestValue;
    private boolean lock;
    private Bid winnerBid;
    private List<Bid> bids;
    private double initialValue;

    public ItemModel(Key key, String description, double initialValue) {
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

            if (getWinnerBid() == null || bid.getValue() > bestValue) {
                bids.add(bid);
                setWinnerBid(bid);
                bestValue = getWinnerBid().getValue();
//                LeilaoDAO.save(LeilaoModel.getInstance());
            }
        }
    }

    public Bid getWinner() {
        return getWinnerBid();
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
        
//        LeilaoDAO.save(LeilaoModel.getInstance());
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
        return bids == null? new ArrayList<Bid>() : bids;
    }

    public double getInitialValue() {
        return initialValue;
    }

	public Bid getWinnerBid() {
		return winnerBid;
	}

	public void setWinnerBid(Bid winnerBid) {
		this.winnerBid = winnerBid;
	}
}
