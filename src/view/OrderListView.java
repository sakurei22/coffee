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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.CoffeeDto;
import dto.SelectOrderDto;
import singleton.Singleton;

public class OrderListView extends JFrame implements  MouseListener, ActionListener{
	Singleton s = Singleton.getInstance();	
	private JTable jtable;
	private JScrollPane jscrPane;
	String columnNames[] = {
			"Espresso Beverages", "Syrup", "size", "샷추가", "휘핑크림", "잔", "총액"	
		};
		
	Object rowData[][];
	DefaultTableModel model; // Table의 넓이를 설정 view에 필요
	List<SelectOrderDto> orderList;
	List<CoffeeDto> list;
	
	JButton addBtn, orderBtn;
	
	public OrderListView() {
		setTitle("COFFEE ORDER LIST");
		setLayout(null);
		JLabel label = new JLabel("주문내역");
		label.setBounds(290, 20, 120, 15);
		add(label);
		list = s.coffeeCtrl.getCoffeeList();
		orderList = s.getOrderList();
		rowData = new Object[orderList.size()][7];
		
		//list에서 테이블로 데이터 삽입
		for (int i = 0; i < orderList.size(); i++) {
			SelectOrderDto dto = orderList.get(i);
			rowData[i][0] = list.get(dto.getCfId()-1).getCfname();
			if(dto.getcSyrup() == 0) {
				rowData[i][1] = "Syrup X";
			} else if(dto.getcSyrup() == 1) {
				rowData[i][1] = "바닐라";
			} else if(dto.getcSyrup() == 2) {
				rowData[i][1] = "캐러멜";
			} else if(dto.getcSyrup() == 3) {
				rowData[i][1] = "헤이즐럿";
			}
			
			if(dto.getcSize() == 0) {
				rowData[i][2] = "Short";
			} else if(dto.getcSize() == 1) {
				rowData[i][2] = "Tall";
			} else if(dto.getcSize() == 2) {
				rowData[i][2] = "Grande";
			}
			
			if(dto.getcShot() == 1) {
				rowData[i][3] = "추가";
			} else {
				rowData[i][3] = "추가안함";
			}
			
			if(dto.getcWhip() == 0) {
				rowData[i][4] = "추가안함";
			} else {
				rowData[i][4] = "추가";
			}
			rowData[i][5] = dto.getcCup();
			rowData[i][6] = dto.getcPrice();
			
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
		jtable.getColumnModel().getColumn(5).setMaxWidth(100);	//잔
		jtable.getColumnModel().getColumn(6).setMaxWidth(100);	//총액
	
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(30, 50, 850, 250);
		add(jscrPane);
		
		addBtn = new JButton("추가 주문");
		addBtn.setBounds(350, 400, 100, 30);
		addBtn.addActionListener(this);
		add(addBtn);
		
		orderBtn = new JButton("주문하기");
		orderBtn.setBounds(460, 400, 100, 30);
		orderBtn.addActionListener(this);
		add(orderBtn);
		
		setBounds(300, 300, 930, 500);
		setBackground(Color.green);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	@Override
	public void mouseClicked(MouseEvent e) {

		
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
		String menuName = list.get(orderList.get(row).getCfId()-1).getCfname();
		int result = JOptionPane.showConfirmDialog(null, menuName + " 주문취소 하시겠습니까?", "주문 취소 확인", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.CLOSED_OPTION) { // 그냥 창 닫았을때
			return;
		} else if(result == JOptionPane.YES_OPTION) { //취소 하겠다 눌렀을때
			orderList.remove(row);
			s.setOrderList(orderList);
			s.coffeeCtrl.menuAdd();
			dispose();
		} else { // 하지않겠다 눌렀을 때
			return;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == addBtn) {
			s.coffeeCtrl.getOrder();
			this.dispose();
		} else if(obj == orderBtn) {
			s.coffeeCtrl.addOrder(s.getLogInId());
			s.coffeeCtrl.addOrderList(orderList);
			orderList.removeAll(orderList);
			s.setOrderList(orderList);
			s.coffeeCtrl.historyList();
			dispose();
		}
		
	}
}
