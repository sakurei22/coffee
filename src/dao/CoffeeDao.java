package dao;

import java.util.List;

import dto.CoffeeDto;
import dto.HistoryDto;
import dto.SelectOrderDto;


public interface CoffeeDao {
	public List<CoffeeDto> getCoffeeList();
	public List<HistoryDto> getHistoryCoffeeList(String id);
	public void addOrderNumber(String id);
	public int selectOrderNum();
	public boolean addOrderList(List<SelectOrderDto> orderList, int orderNum);
}
