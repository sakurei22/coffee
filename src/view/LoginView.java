package view;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Singleton;

public class LoginView extends JFrame implements ActionListener{

	private JTextField tfId;
	private JPasswordField tfPw;
	JButton btnLogin, btnJoin;
	
	
	public LoginView() {
		setTitle("LOGIN");
		setLayout(null);
		
		Label label = new Label("id:");
		label.setBounds(60, 60, 30, 30);
		add(label);
		
		tfId = new JTextField();
		tfId.setBounds(100, 60, 100, 30);
		add(tfId);
		
		Label label2 = new Label("pw:");
		label2.setBounds(60, 100, 30, 30);
		add(label2);
		
		tfPw = new JPasswordField();
		tfPw.setBounds(100, 100, 100, 30);
		tfPw.addActionListener(this);
		add(tfPw);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(50, 150, 80, 30);
		btnLogin.addActionListener(this);
		add(btnLogin);
		
		btnJoin = new JButton("Join");
		btnJoin.setBounds(150, 150, 80, 30);
		btnJoin.addActionListener(this);
		add(btnJoin);
		
		setBounds(300, 300, 280, 280);
		setBackground(Color.green);
		setVisible(true);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		Object obj = e.getSource();
		if(obj == btnLogin || obj == tfPw) {
			String id = tfId.getText().trim();
			String pwd = tfPw.getText().trim();
			s.memCtrl.logInAf(id, pwd);
			this.dispose();
			
		} else if (obj == btnJoin) { //회원가입
			s.memCtrl.regi();
			this.dispose();
		}
	}
}
	/*
	@Override
	public void actionPerformed(ActionEvent e) {
		MemberDao md = MemberDao.getInstance();
		MemberDto dto;
		Object obj = e.getSource();
		if(obj == btnLogin || obj == tfPw) {
			String id = tfId.getText().trim();
			String pwd = tfPw.getText().trim();
			dto = md.loginMember(id, pwd);
			
			if(dto != null) {
				JOptionPane.showMessageDialog(null, dto.getName()+ "님 환영합니다.");
				new BBSView();
				//login → id를 저장
				md.setLoginId(dto.getId());
				// web에서는 세션에 저장
				
			} else {
				JOptionPane.showMessageDialog(null, "ID 혹은 패스워드가 틀렸습니다.");
				tfPw.setText("");
				tfId.setText("");
				return;
			}
			
			
		} else {
			new JoinView();
		}
		this.dispose();
	}
*/

