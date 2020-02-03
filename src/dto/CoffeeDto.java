package dto;
/*
CREATE TABLE COFFEE (
	CFID NUMBER(3) PRIMARY KEY,
	CFNAME VARCHAR2(30) NOT NULL,
	CFPSHORT NUMBER(5) NOT NULL,
	CFPTALL NUMBER(5) NOT NULL,
	CFPGRANDE NUMBER(5) NOT NULL,
	AUTH NUMBER(1) NOT NULL
);

 */
public class CoffeeDto {
	private int cfid; 
	private String cfname;
	private int cfpShot;
	private int cfpTall;
	private int cfpGrande;
	private int auth;  //판매중인지 판매중지인지 변경해주기 위해
	
	
	public int getCfid() {
		return cfid;
	}
	public void setCfid(int cfid) {
		this.cfid = cfid;
	}
	public String getCfname() {
		return cfname;
	}
	public void setCfname(String cfname) {
		this.cfname = cfname;
	}
	public int getCfpShot() {
		return cfpShot;
	}
	public void setCfpShot(int cfpShot) {
		this.cfpShot = cfpShot;
	}
	public int getCfpTall() {
		return cfpTall;
	}
	public void setCfpTall(int cfpTall) {
		this.cfpTall = cfpTall;
	}
	public int getCfpGrande() {
		return cfpGrande;
	}
	public void setCfpGrande(int cfpGrande) {
		this.cfpGrande = cfpGrande;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public CoffeeDto(int cfid, String cfname, int cfpShot, int cfpTall, int cfpGrande, int auth) {
		super();
		this.cfid = cfid;
		this.cfname = cfname;
		this.cfpShot = cfpShot;
		this.cfpTall = cfpTall;
		this.cfpGrande = cfpGrande;
		this.auth = auth;
	}
	@Override
	public String toString() {
		return "CoffeeDto [cfid=" + cfid + ", cfname=" + cfname + ", cfpShot=" + cfpShot + ", cfpTall=" + cfpTall
				+ ", cfpGrande=" + cfpGrande + ", auth=" + auth + "]";
	}
	
	public CoffeeDto() {
	}
	
	
}
