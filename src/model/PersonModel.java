package model;

import java.io.Serializable;
import java.util.HashMap;

public class PersonModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String pass;
	private boolean isAdmin = false;
	
	private static HashMap<String, PersonModel> persons;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public static HashMap<String, PersonModel> getPersons() {
		return persons;
	}
	public static void setPersons(HashMap<String, PersonModel> persons) {
		PersonModel.persons = persons;
	}
	
}
