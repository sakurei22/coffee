package service;

import java.util.List;

import dto.CoffeeDto;
import dto.HistoryDto;
import dto.SelectOrderDto;

public interface CoffeeService {
	public List<CoffeeDto> getCoffeeList();
	public void addOrderNumber(String id);
	public List<HistoryDto> getHistoryCoffeeList(String id);
	public int selectOrderNum();
	public boolean addOrderList(List<SelectOrderDto> orderList, int orderNum);
}
