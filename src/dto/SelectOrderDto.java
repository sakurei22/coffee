package dto;
/*
 CREATE TABLE ORDER_LIST(
	SEQ 	 NUMBER(4) PRIMARY KEY,
	ORDERNUM NUMBER(4) NOT NULL, 
	CFID 	 NUMBER(3) NOT NULL, 
	C_SYRUP  NUMBER(1) NOT NULL,
	C_SIZE 	 NUMBER(1) NOT NULL,
	C_SHOT 	 NUMBER(1),
	C_WHIP 	 NUMBER(1),
	C_CUP 	 NUMBER(3) NOT NULL,
	C_PRICE  NUMBER(9)
);

CREATE SEQUENCE SEQ_CF_ORDER
START WITH 1
INCREMENT BY 1;

ALTER TABLE ORDER_LIST
ADD CONSTRAINT FK_ORDER2_ID FOREIGN KEY(CFID)
	REFERENCES COFFEE(CFID);
	
	ALTER TABLE ORDER_LIST
ADD CONSTRAINT FK_ORDER_ID FOREIGN KEY(ORDERNUM)
	REFERENCES ORDERCOFFEE(ORDERNUM);
 */
public class SelectOrderDto {
	private int seq;
	private int orderNum;  //주문번호
	private int cfId;	   //메뉴번호
	private int cSyrup;
	private int cSize;
	private int cShot;
	private int cWhip;
	private int cCup;
	private int cPrice;
	
	public SelectOrderDto() {
	}
	
	
	@Override
	public String toString() {
		return "SelectOrderDto [seq=" + seq + ", orderNum=" + orderNum + ", cfId=" + cfId + ", cSyrup=" + cSyrup
				+ ", cSize=" + cSize + ", cShot=" + cShot + ", cWhip=" + cWhip + ", cCup=" + cCup + ", cPrice=" + cPrice
				+ "]";
	}

	public SelectOrderDto(int cfId, int cSyrup, int cSize, int cShot, int cWhip, int cCup, int cPrice) {
		super();
		this.cfId = cfId;
		this.cSyrup = cSyrup;
		this.cSize = cSize;
		this.cShot = cShot;
		this.cWhip = cWhip;
		this.cCup = cCup;
		this.cPrice = cPrice;
	}


	public SelectOrderDto(int seq, int orderNum, int cfId, int cSyrup, int cSize, int cShot, int cWhip, int cCup,
			int cPrice) {
		super();
		this.seq = seq;
		this.orderNum = orderNum;
		this.cfId = cfId;
		this.cSyrup = cSyrup;
		this.cSize = cSize;
		this.cShot = cShot;
		this.cWhip = cWhip;
		this.cCup = cCup;
		this.cPrice = cPrice;
	}



	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getCfId() {
		return cfId;
	}
	public void setCfId(int cfId) {
		this.cfId = cfId;
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
	
	
}
