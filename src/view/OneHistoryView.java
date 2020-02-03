package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dto.CoffeeDto;
import dto.HistoryDto;
import singleton.Singleton;

public class OneHistoryView extends JFrame implements ActionListener{
	Singleton s = Singleton.getInstance();
	
	JButton orderBtn, historyBtn;
	List<CoffeeDto> list;
	
	public OneHistoryView(HistoryDto dto) {
		setTitle("영수증");
		setLayout(null);
		JLabel orderNumber = new JLabel("주문번호 : " + dto.getOrdernum());
		orderNumber.setBounds(100, 10, 200 , 30);
		add(orderNumber);
		JLabel id = new JLabel(s.getLogInId());
		id.setBounds(130, 50, 100, 30);
		add(id);
		
		JLabel orderDate = new JLabel(dto.getOrderDate());
		orderDate.setBounds(130, 90, 200, 30);
		add(orderDate);
		
		list = s.coffeeCtrl.getCoffeeList();
		String orderS = "";
		
		for (int i = 0; i < list.size(); i++) {
			if(dto.getCfid() == list.get(i).getCfid()) {
				
				if(dto.getcSize() == 0) {
					orderS = "S) " + list.get(i).getCfname()				
					 + "  -------  " + list.get(i).getCfpShot()
					 + "   " + dto.getcCup() + "잔"; 
				} else if(dto.getcSize() ==1) {
					orderS = "T) " + list.get(i).getCfname()					
					+ "  -------  " + list.get(i).getCfpTall()
					+ "   " + dto.getcCup() + "잔";
				} else if(dto.getcSize() == 2) {
					orderS = "G) "+ list.get(i).getCfname()
							+ "  -------  " + list.get(i).getCfpGrande()
							+ "   " + dto.getcCup() + "잔";
				}
				
			}
		}
		JLabel orderName = new JLabel(orderS);
		orderName.setBounds(30, 130, 230, 30);
		add(orderName);
		
		String orderSyrup ="";
		if(dto.getcSyrup() == 0) {
			orderSyrup ="└ 시럽추가안함";
		} else if(dto.getcSyrup() ==1 ) {
			orderSyrup = "└ 바닐라 시럽  ----------- 500";
		} else if(dto.getcSyrup() == 2) {
			orderSyrup = "└ 캐러멜 시럽  ----------- 500";
		} else if(dto.getcSyrup() == 3) {
			orderSyrup = "└ 헤이즐럿 시럽  ----------- 500";
		}
		
		JLabel orderSyrp = new JLabel(orderSyrup);
		orderSyrp.setBounds(30, 150, 200, 30);
		add(orderSyrp);
		
		String orderShot = "└ 샷추가 안함";
		if(dto.getcShot() == 1) {
			orderShot = "└ 샷추가-------------500";
		}
		JLabel orderSot = new JLabel(orderShot);
		orderSot.setBounds(30, 170, 200, 30);
		add(orderSot);
		
		String orderWhip = "└ 휘핑추가 안함";
		if(dto.getcWhip() == 1) {
			orderWhip = "└ 휘핑추가----------500";
		}
		
		JLabel orderWh = new JLabel(orderWhip);
		orderWh.setBounds(30, 190, 200, 30);
		add(orderWh);
		
		
			
		
		JLabel orderPrice = new JLabel("Total : " + dto.getcPrice());
		orderPrice.setBounds(150, 290, 100, 30);
		add(orderPrice);
		orderBtn = new JButton("주문하기");
		orderBtn.setBounds(30, 400, 100, 30);
		orderBtn.addActionListener(this);
		add(orderBtn);
		historyBtn = new JButton("주문내역");
		historyBtn.setBounds(140, 400, 100, 30);
		historyBtn.addActionListener(this);
		add(historyBtn);
		setBounds(300, 300, 300, 500);
		setBackground(Color.green);
		setVisible(true);		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == orderBtn) {
			s.coffeeCtrl.getOrder();
			dispose();
		} else if(obj == historyBtn) {
			s.coffeeCtrl.historyList();
			dispose();
		}
		
	}
}
