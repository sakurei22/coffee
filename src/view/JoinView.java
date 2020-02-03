package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Singleton;
public class JoinView extends JFrame implements ActionListener{
	
	JLabel label[] = new JLabel[4];
	JTextField textField[] = new JTextField[4];
	JButton insertBtn, idCheckBtn;
	
	public JoinView() {
		setTitle("회원가입");
		setLayout(null);
		
		String title[] = { "ID:", "PW:", "NAME:", "EMAIL:"};
		
		for (int i = 0; i < title.length; i++) {
			label[i] = new JLabel();
			label[i].setText( title[i] );
			label[i].setBounds(30, 50 + 40 * i, 100, 30);
			
			textField[i] = new JTextField();
			textField[i].setBounds(180, 50 + 40 * i, 200, 30);
			
			add(label[i]);
			add(textField[i]);
		}
		
		idCheckBtn = new JButton("ID 확인");
		idCheckBtn.setBounds(390, 50, 100, 30);
		idCheckBtn.addActionListener(this);
		add(idCheckBtn);
		insertBtn = new JButton("JOIN");
		insertBtn.setBounds(230, 240, 150, 50);
		insertBtn.addActionListener(this);
		add(insertBtn);
				
		setBounds(300, 300, 600, 350);
		setBackground(Color.green);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		
		Object obj = e.getSource();
		if(obj == insertBtn) { // 회원가입
			for (int i = 0; i < 4; i++) {
				if(textField[i].getText().equals("")) {
					JOptionPane.showMessageDialog(null, "모두 작성해 주십시오");
					return;
				}
			}
			s.memCtrl.regiAf(textField[0].getText(), textField[1].getText(), 
						textField[2].getText(), textField[3].getText());
			
			this.dispose();

		} else if (obj == idCheckBtn){ // id 중복체크
			if(textField[0].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "id를 입력해주십시오");
				return;
			}
			boolean b = s.memCtrl.idCheck(textField[0].getText().trim());
			
			if(b) {
				JOptionPane.showMessageDialog(null, textField[0].getText() + "사용 가능한 ID입니다.");
			} else {
				JOptionPane.showMessageDialog(null, "사용할 수 없는 ID입니다.");
				textField[0].setText("");
			}
		}
		/*
		MemberDao md = MemberDao.getInstance();
		
		Object obj = e.getSource();
		if(obj == insertBtn) {
			for (int i = 0; i < 4; i++) {
				if(textField[i].getText().equals("")) {
					JOptionPane.showMessageDialog(null, "모두 작성해 주십시오");
					return;
				}
			}
			
			String id = textField[0].getText();
			String pwd = textField[1].getText();
			String name = textField[2].getText();
			String email = textField[3].getText();
			
			MemberDto dto = new MemberDto(id, pwd, name, email, 1);
			
			
			boolean insertVal = md.addMember(dto);
			
			if(insertVal == true) {
				JOptionPane.showMessageDialog(null, "가입을 축하드립니다!");
				new LoginView();
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "실패하였습니다.");
				return;
			}
		} else {
			if(textField[0].getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "id를 입력해주십시오");
				return;
			}
			boolean idok = md.selectId(textField[0].getText().trim());
			if(idok) {
				JOptionPane.showMessageDialog(null, textField[0].getText() + "사용 할 수 있는 id입니다");
			} else {
				JOptionPane.showMessageDialog(null, "중복된 id입니다.");
				textField[0].setText("");
				return;
			}
			
		}*/
	}
}
