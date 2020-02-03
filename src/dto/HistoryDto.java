package dto;

public class HistoryDto {
	private int seq;
	private int ordernum;
	private int cfid;
	private int cSyrup;
	private int cSize;
	private int cShot;
	private int cWhip;
	private int cCup;
	private int cPrice;
	private String orderDate;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public int getCfid() {
		return cfid;
	}
	public void setCfid(int cfid) {
		this.cfid = cfid;
	}
	public int getcSyrup() {
		return cSyrup;
	}
	public void setcSyrup(int cSyrup) {
		this.cSyrup = cSyrup;
	}
	public int getcSize() {
		return cSize;
	}
	public void setcSize(int cSize) {
		this.cSize = cSize;
	}
	public int getcShot() {
		return cShot;
	}
	public void setcShot(int cShot) {
		this.cShot = cShot;
	}
	public int getcWhip() {
		return cWhip;
	}
	public void setcWhip(int cWhip) {
		this.cWhip = cWhip;
	}
	public int getcCup() {
		return cCup;
	}
	public void setcCup(int cCup) {
		this.cCup = cCup;
	}
	public int getcPrice() {
		return cPrice;
	}
	public void setcPrice(int cPrice) {
		this.cPrice = cPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public HistoryDto(int seq, int ordernum, int cfid, int cSyrup, int cSize, int cShot, int cWhip, int cCup,
			int cPrice, String orderDate) {
		super();
		this.seq = seq;
		this.ordernum = ordernum;
		this.cfid = cfid;
		this.cSyrup = cSyrup;
		this.cSize = cSize;
		this.cShot = cShot;
		this.cWhip = cWhip;
		this.cCup = cCup;
		this.cPrice = cPrice;
		this.orderDate = orderDate;
	}
	public HistoryDto() {
		super();
	}
	

}
