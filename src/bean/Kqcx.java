package bean;

public class Kqcx {

	private String username;
	private String kq;
	private String jq;
	private String cc;
	private String jb;
	private String tx;
	public Kqcx(){
		
	}
	public Kqcx(String username, String kq, String jq, String cc, String jb,
			String tx) {
		super();
		this.username = username;
		this.kq = kq;
		this.jq = jq;
		this.cc = cc;
		this.jb = jb;
		this.tx = tx;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getKq() {
		return kq;
	}
	public void setKq(String kq) {
		this.kq = kq;
	}
	public String getJq() {
		return jq;
	}
	public void setJq(String jq) {
		this.jq = jq;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getJb() {
		return jb;
	}
	public void setJb(String jb) {
		this.jb = jb;
	}
	public String getTx() {
		return tx;
	}
	public void setTx(String tx) {
		this.tx = tx;
	}



	
}
