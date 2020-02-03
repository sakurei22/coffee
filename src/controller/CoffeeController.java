package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dto.CoffeeDto;
import dto.HistoryDto;
import dto.SelectOrderDto;
import service.CoffeeService;
import service.impl.CoffeeServiceImpl;
import view.CoffeeMenuView;
import view.OneHistoryView;
import view.OrderHistoryView;
import view.OrderListView;
import view.OrderView;

public class CoffeeController {
	CoffeeService coffServ = new CoffeeServiceImpl();
	CoffeeMenuView cmv = null;
	public List<CoffeeDto> getCoffeeList(){
		List<CoffeeDto> list = coffServ.getCoffeeList();
		return list;
	}
	
	public List<HistoryDto> getHistoryCoffeeList(String id){
		List<HistoryDto> list = coffServ.getHistoryCoffeeList(id);
		return list;
	}
	
	public void historyList() {
		new OrderHistoryView();
	}
	
	public void getOrder() {
		new OrderView();
	}
	
	public boolean menuOk() {
		boolean b;
		if(cmv == null) {
			b = false;
		} else {
			b = true;
		}
		return b;
	}
	
	public void menuCheck() {
		cmv = new CoffeeMenuView();
	}
	
	public void menuClose() {
		cmv.dispose();
		cmv = null;
	}
	
	public void menuAdd() {
		new OrderListView();
	}
	
	public void addOrder(String id) {
		coffServ.addOrderNumber(id);
	}
	
	public void addOrderList(List<SelectOrderDto> orderList) {
		int orderNum = coffServ.selectOrderNum();
		boolean orderInput = coffServ.addOrderList(orderList, orderNum);
		if(orderInput == true) {
			JOptionPane.showMessageDialog(null, orderNum + "번 주문이 완료되었습니다.");
		} 
	}
	
	public void oneHistoryView(HistoryDto dto) {
		new OneHistoryView(dto);
	}
}
