package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dto.CoffeeDto;
import dto.HistoryDto;
import dto.SelectOrderDto;
import singleton.Singleton;

public class OrderView extends JFrame implements ActionListener{
	Singleton s = Singleton.getInstance();	
	JButton menuBtn, orderBtn, historyBtn, logoutBtn;
	JComboBox<String> menuChoice;
	String menu[];
	JPanel sizePanel, syrupPanel, etcPanel, customPanel;
	JLabel sizeLabel, syrupLabel, etcLabel, orderCupLabel;
	JCheckBox plusShot, pluswhipped; 
	JRadioButton rbShort, rbTall, rbGrande, rbVainilla, rbCaramel, rbHazelnut, rbNoSyrup;
	ButtonGroup sizeGroup, syrupGroup;
	JTextField orderCup;
	
	boolean disp;
	
	List<HistoryDto> historyList;
	List<CoffeeDto> list;
	List<SelectOrderDto> orderList;
	
	public OrderView() {
		setTitle("COFFEE ORDER");
		setLayout(null);
		
		//MENU LIST SHOW BUTTON
		menuBtn = new JButton("MENU LIST");
		menuBtn.setBounds(250, 10, 100, 30);
		menuBtn.addActionListener(this);
		add(menuBtn);
		
		//HISTORY BUTTON
		historyBtn = new JButton("HISTORY");
		historyBtn.setBounds(360, 10, 100, 30);
		historyBtn.addActionListener(this);
		add(historyBtn);
		
		//LOGOUT BUTTON
		logoutBtn = new JButton("LOGOUT");
		logoutBtn.setBounds(470, 10, 100, 30);
		logoutBtn.addActionListener(this);
		add(logoutBtn);
		
		//MENU LIST CHOICE
		list = s.coffeeCtrl.getCoffeeList();
		menu = new String[list.size()+1];
		menu[0] = "Menu Choice";
		for (int i = 0; i < list.size(); i++) {
			menu[i+1] = list.get(i).getCfname();
		}
		menuChoice = new JComboBox<String>(menu);
		menuChoice.setBounds(80, 50, 350, 30);
		add(menuChoice);
		
		//COFFEE CUSTOM
		customPanel = new JPanel();
		customPanel.setLayout(new GridLayout(1,3));
		customPanel.setBounds(80, 100, 500, 150);
		
		//CUSTOM 필수 조건(SIZE)
		sizePanel = new JPanel();
		sizePanel.setBounds(0, 0, 100, 150);
		sizePanel.setLayout(new GridLayout(4,1));
		
		sizeLabel = new JLabel("크기");
		sizePanel.add(sizeLabel);
		rbShort = new JRadioButton("SHORT");
		rbTall = new JRadioButton("TALL");
		rbGrande = new JRadioButton("GRANDE");
		
		sizeGroup = new ButtonGroup();
		sizeGroup.add(rbShort);
		sizeGroup.add(rbTall);
		sizeGroup.add(rbGrande);
		
		rbTall.setSelected(true); // 기본값 TALL SIZE
		sizePanel.add(rbShort);
		sizePanel.add(rbTall);
		sizePanel.add(rbGrande);
		customPanel.add(sizePanel);
		
		//필수 선택 
		syrupPanel = new JPanel();
		syrupPanel.setBounds(0, 0, 100, 150);
		syrupPanel.setLayout(new GridLayout(5,1));
		syrupLabel = new JLabel("Syrup(+500)");
		syrupPanel.add(syrupLabel);
		
		rbVainilla = new JRadioButton("Vainilla");
		rbCaramel = new JRadioButton("Caramel");
		rbHazelnut = new JRadioButton("Hazelnut");
		rbNoSyrup = new JRadioButton("Syrup x");
		
		syrupGroup = new ButtonGroup();
		syrupGroup.add(rbVainilla);
		syrupGroup.add(rbCaramel);
		syrupGroup.add(rbHazelnut);
		syrupGroup.add(rbNoSyrup);
		rbNoSyrup.setSelected(true); // 기본값 시럽 없음
		
		syrupPanel.add(rbVainilla);
		syrupPanel.add(rbCaramel);
		syrupPanel.add(rbHazelnut);
		syrupPanel.add(rbNoSyrup);
		customPanel.add(syrupPanel);
		
		//선택 가능한 샷 추가, 휘핑크림 추가
		etcPanel = new JPanel();
		etcPanel.setBounds(0, 0, 100, 150);
		etcPanel.setLayout(new GridLayout(3,1));
		etcLabel = new JLabel("etc(각, +500)");
		etcPanel.add(etcLabel);
		plusShot = new JCheckBox("샷추가");  	
		pluswhipped = new JCheckBox("휘핑크림");
		etcPanel.add(plusShot);
		etcPanel.add(pluswhipped);
		customPanel.add(etcPanel);		
		add(customPanel);
		
		orderCup = new JTextField();
		orderCup.setBounds(200, 280, 80, 30);
		add(orderCup);
		
		orderCupLabel = new JLabel("잔");
		orderCupLabel.setBounds(290, 280, 50, 30);
		add(orderCupLabel);
		
		orderBtn = new JButton("담기");
		orderBtn.addActionListener(this);
		orderBtn.setBounds(340, 280, 120, 30);
		add(orderBtn);
		
		setBounds(300, 300, 600, 500);
		setBackground(Color.green);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == menuBtn) {
			disp = s.coffeeCtrl.menuOk();
			
			if(disp == false) {
				s.coffeeCtrl.menuCheck();
			} else {
				s.coffeeCtrl.menuClose();
			}
			
		} else if(obj == orderBtn) {
			int comboNum = menuChoice.getSelectedIndex();
			SelectOrderDto selectDto;
			if(comboNum == 0) {
				JOptionPane.showMessageDialog(null, "메뉴를 선택해 주세요.");
				return;
			}
			int cSize = 1;
			int price = 0;
			if(rbShort.isSelected()) {
				cSize = 0;
				price = list.get(comboNum-1).getCfpShot();
			} else if(rbTall.isSelected()) {
				cSize = 1;
				price = list.get(comboNum-1).getCfpTall();
			}else if(rbGrande.isSelected()) {
				cSize = 2;
				price = list.get(comboNum-1).getCfpGrande();
			}
			
			int cSyrup = 0;
			if(rbVainilla.isSelected()) {
				cSyrup = 1;
				price = price + 500;
			} else if (rbCaramel.isSelected()) {
				cSyrup = 2;
				price = price + 500;
			} else if (rbHazelnut.isSelected()) {
				cSyrup = 3;
				price = price + 500;
			} else if (rbNoSyrup.isSelected()) {
				cSyrup = 0;
			}
			
			int cShot = 0;
			int cWhip = 0;
			
			if(plusShot.isSelected()) {
				cShot = 1;
				price = price + 500;
			}
			
			if(pluswhipped.isSelected()) {
				cWhip = 1;
				price = price + 500;
			}
			
			String cCup = orderCup.getText();
			if(cCup.equals("")||cCup.equals("0")) {
				JOptionPane.showMessageDialog(null, "몇 잔을 주문하실지 입력해 주세요");
				orderCup.setText("");
				return;
			}
			boolean b = true;
			for(int i = 0; i < cCup.length(); i++) {
				char c = cCup.charAt(i);
				if(c < 48 || c >57) {
					b = false;
				}
			}
			
			if(b == false) {
				JOptionPane.showMessageDialog(null, "숫자만 입력해 주세요.");
				b = true;
				orderCup.setText("");
				return;
			}
			
			price = price * Integer.parseInt(cCup);
			
			selectDto = new SelectOrderDto(list.get(comboNum-1).getCfid(), cSyrup, cSize, 
										cShot, cWhip, Integer.parseInt(cCup), price);
			
			orderList = s.getOrderList();
			
			orderList.add(selectDto);
			s.setOrderList(orderList);
			s.coffeeCtrl.menuAdd();
			dispose();
			
		} else if(obj == historyBtn) {
			s.coffeeCtrl.historyList();			
			dispose();
			
		} else if(obj == logoutBtn) {
			s.memCtrl.logOut();
			dispose();
		}
		
		
	}
}
