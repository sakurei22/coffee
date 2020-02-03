package singleton;

import java.util.List;

import controller.CoffeeController;
import controller.MemberController;
import dto.SelectOrderDto;

public class Singleton {
	private static Singleton s = null;
	public MemberController memCtrl = null;
	public CoffeeController coffeeCtrl = null;
	private String logInId = null;
	private List<SelectOrderDto> orderList = null;

	
	
	public List<SelectOrderDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<SelectOrderDto> orderList) {
		this.orderList = orderList;
	}

	public String getLogInId() {
		return logInId;
	}

	public void setLogInId(String logInId) {
		this.logInId = logInId;
	}

	private Singleton() {
		memCtrl = new MemberController();
		coffeeCtrl = new CoffeeController();
	}
	
	public static Singleton getInstance() {
		if(s == null) {
			s = new Singleton();
		}
		return s;
	}
}
