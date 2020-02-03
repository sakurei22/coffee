package view;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.CoffeeDto;
import singleton.Singleton;

public class CoffeeMenuView extends JFrame implements WindowListener{
	private JTable jtable;
	private JScrollPane jscrPane;
	String columnNames[] = {
			"Espresso Beverages", "Short", "Tall", "Grande"	
		};
		
		Object rowData[][];
		DefaultTableModel model; // Table의 넓이를 설정 view에 필요
		List<CoffeeDto> list = null;
		Singleton s = Singleton.getInstance();
		
	public CoffeeMenuView() {
		setTitle("COFFEE ORDER");
		setLayout(null);
		JLabel label = new JLabel("가격표");
		label.setBounds(290, 20, 120, 15);
		add(label);
		
		list = s.coffeeCtrl.getCoffeeList();
		rowData = new Object[list.size()][4];
		
		//list에서 테이블로 데이터 삽입
		for (int i = 0; i < list.size(); i++) {
			CoffeeDto dto = list.get(i);
			rowData[i][0] = dto.getCfname();
			rowData[i][1] = dto.getCfpShot();
			rowData[i][2] = dto.getCfpTall();
			rowData[i][3] = dto.getCfpGrande();
			
		}
		//테이블 관련
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
		
		//테이블 생성
		jtable = new JTable(model);
		
		//각 column의 폭을 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(250);	//메뉴명
		jtable.getColumnModel().getColumn(1).setMaxWidth(100); 	//short
		jtable.getColumnModel().getColumn(2).setMaxWidth(100);	//tall
		jtable.getColumnModel().getColumn(3).setMaxWidth(100);	//grande
		// 테이블의 column의 글의 맞춤(왼쪽, 중간, 오른쪽)
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	// 중간
				
		// '번호'와 '작성자'의 컬럼은 글의 중간 맞춤이 된다
		jtable.getColumn("Espresso Beverages").setCellRenderer(celAlignCenter);
		jtable.getColumn("Short").setCellRenderer(celAlignCenter);
		jtable.getColumn("Tall").setCellRenderer(celAlignCenter);
		jtable.getColumn("Grande").setCellRenderer(celAlignCenter);
		
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(30, 50, 550, 183);
		add(jscrPane);
		
		setBounds(300, 300, 630, 300);
		setBackground(Color.green);
		setVisible(true);


		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		s.coffeeCtrl.menuClose();
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		s.coffeeCtrl.menuClose();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
