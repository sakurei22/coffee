package dto;
/*
 CREATE TABLE ORDERCOFFEE(
	ORDERNUM NUMBER(4) PRIMARY KEY,
	MEMBERID VARCHAR2(30) NOT NULL,
	ORDERDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_ORDER
START WITH 100
INCREMENT BY 1;

ALTER TABLE ORDERCOFFEE
ADD CONSTRAINT FK_CFORDER_ID FOREIGN KEY(MEMBERID)
	REFERENCES MEMBER(ID);
 */

public class OrderCoffeeDto {
	private int orderNum;
	private String memId;
	private String orderdate;
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public OrderCoffeeDto(int orderNum, String memId, String orderdate) {
		super();
		this.orderNum = orderNum;
		this.memId = memId;
		this.orderdate = orderdate;
	}
	
	public OrderCoffeeDto() {
	}
}
