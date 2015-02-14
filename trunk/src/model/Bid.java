package model;

import java.io.Serializable;

public class Bid implements Serializable {

	private static final long serialVersionUID = 1L;
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