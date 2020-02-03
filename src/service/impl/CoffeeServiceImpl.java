package service.impl;

import java.util.List;

import dao.CoffeeDao;
import dao.impl.CoffeeDaoImple;
import dto.CoffeeDto;
import dto.HistoryDto;
import dto.SelectOrderDto;
import service.CoffeeService;

public class CoffeeServiceImpl implements CoffeeService {
	CoffeeDao dao = new CoffeeDaoImple();

	@Override
	public List<CoffeeDto> getCoffeeList() {
		return dao.getCoffeeList();
	}

	@Override
	public void addOrderNumber(String id) {
		dao.addOrderNumber(id);
		
	}

	@Override
	public boolean addOrderList(List<SelectOrderDto> orderList, int orderNum) {
		return dao.addOrderList(orderList, orderNum);
		
	}

	@Override
	public int selectOrderNum() {
		return dao.selectOrderNum();
	}

	@Override
	public List<HistoryDto> getHistoryCoffeeList(String id) {
		return dao.getHistoryCoffeeList(id);
	} 
}
