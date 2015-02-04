package managedBeans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Authenticator {
	private String id;
	private String pass;
	
	private static final String idAdmin = "admin";
	private static final String passAdmin = "flop123";
	
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
	
	public String check(){
		if(id.equals(idAdmin) && pass.equals(passAdmin)){
			return "leilao";
		}
		return "index";
		
	}
}
