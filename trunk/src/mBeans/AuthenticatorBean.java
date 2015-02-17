package mBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AuthenticatorBean {
	
	private String id;
	private String pass;
	private boolean authenticated = false;
	private boolean admin = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean isAdmin) {
	}
	
	public String check(){
		// TODO implementar controle do login aqui
		authenticated = true;
		admin=true;
		return "leilao";
	}
	
	public String logout(){
		authenticated = false;
		admin = false;
		
		return "login";
	}
}
