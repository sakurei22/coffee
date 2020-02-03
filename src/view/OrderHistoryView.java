package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.CoffeeDto;
import dto.HistoryDto;
import dto.SelectOrderDto;
import singleton.Singleton;

public class OrderHistoryView extends JFrame implements ActionListener, MouseListener{
	Singleton s = Singleton.getInstance();	
	private JTable jtable;
	private JScrollPane jscrPane;
	String columnNames[] = {
			"Espresso Beverages", "주문일", "사이즈", "잔", "총액"	
		};
		
	Object rowData[][];
	DefaultTableModel model; // Table의 넓이를 설정 view에 필요
	List<HistoryDto> historyList;
	List<CoffeeDto> list;
	int totalPrice;
	JButton orderBtn, logoutBtn;
	
	public OrderHistoryView() {
		setTitle("COFFEE ORDER HISTORY");
		setLayout(null);
		JLabel label = new JLabel("주문내역");
		label.setBounds(290, 20, 120, 15);
		add(label);
		totalPrice = 0;
		list = s.coffeeCtrl.getCoffeeList();
		historyList = s.coffeeCtrl.getHistoryCoffeeList(s.getLogInId());
		rowData = new Object[historyList.size()][5];
		
		//list에서 테이블로 데이터 삽입
		for (int i = 0; i < historyList.size(); i++) {
			HistoryDto dto = historyList.get(i);
			rowData[i][0] = list.get(dto.getCfid()-1).getCfname();
			rowData[i][1] = historyList.get(i).getOrderDate();
			
			if(dto.getcSize() == 0) {
				rowData[i][2] = "Short";
			} else if(dto.getcSize() == 1) {
				rowData[i][2] = "Tall";
			} else if(dto.getcSize() == 2) {
				rowData[i][2] = "Grande";
			}
			rowData[i][3] = dto.getcCup();
			rowData[i][4] = dto.getcPrice();
			totalPrice = totalPrice +dto.getcPrice();
			
		}
		//테이블 관련
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
		
		//테이블 생성
		jtable = new JTable(model);
		jtable.addMouseListener(this);
		
		//각 column의 폭을 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(250);	//메뉴명
		jtable.getColumnModel().getColumn(1).setMaxWidth(100); 	//시럽
		jtable.getColumnModel().getColumn(2).setMaxWidth(100);	//크기
		jtable.getColumnModel().getColumn(3).setMaxWidth(100);	//샷추가
		jtable.getColumnModel().getColumn(4).setMaxWidth(100);	//휘핑크림
	
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(30, 50, 650, 250);
		add(jscrPane);
		
		logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(360, 400, 100, 30);
		logoutBtn.addActionListener(this);
		add(logoutBtn);
		
		orderBtn = new JButton("주문하기");
		orderBtn.setBounds(250, 400, 100, 30);
		orderBtn.addActionListener(this);
		add(orderBtn);
		
		JLabel total = new JLabel("Total : " + totalPrice);
		total.setBounds(600, 300, 150, 30);
		add(total);
		
		setBounds(300, 300, 730, 500);
		setBackground(Color.green);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == orderBtn) {
			s.coffeeCtrl.getOrder();
			this.dispose();
		} else if (obj == logoutBtn) {
			s.memCtrl.logOut();
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int row = jtable.getSelectedRow();
		HistoryDto dto =  historyList.get(row);
		s.coffeeCtrl.oneHistoryView(dto);
		dispose();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
