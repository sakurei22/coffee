package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MemberDto;
import dto.SelectOrderDto;
import service.MemberService;
import service.impl.MemberServiceImpl;
import singleton.Singleton;
import view.JoinView;
import view.LoginView;

//@Controller 스프림 프레임워크에선 생성해준다

//service와 대화
public class MemberController {
	MemberService memServ = new MemberServiceImpl();
	
	public void logIn() { //로그인 화면 호출
		new LoginView();
	}
	
	public void regi() { // 회원가입 화면으로 이동
		new JoinView();
	}
	
	public boolean idCheck(String id) {
		return memServ.selectId(id);
	}
	
	public void regiAf(String id, String pwd, String name, String email) {
		boolean b = memServ.addMember(new MemberDto(id, pwd, name, email, 3));
		if(b) {
			JOptionPane.showMessageDialog(null, "회원가입 성공");
			logIn();
		} else {
			JOptionPane.showMessageDialog(null, "회원가입 실패");
			regi();
		}
	}
	
	public void logInAf(String id, String pwd) {
		MemberDto dto = memServ.loginMember(id, pwd);
		
		if(dto == null) {
			JOptionPane.showMessageDialog(null, "ID 혹은 PW가 틀렸습니다.");
			logIn();
		} else {
			JOptionPane.showMessageDialog(null, dto.getId()+"님 환영합니다.");
			//id 저장 - web은 session 
			Singleton s = Singleton.getInstance();
			s.setLogInId(dto.getId());
		
			//coffeeController로 이동
			List<SelectOrderDto> orderList = new ArrayList<SelectOrderDto>();
			s.setOrderList(orderList);
			s.coffeeCtrl.getOrder();
			
		}
	}
	
	public void logOut() {
		Singleton s = Singleton.getInstance();
		JOptionPane.showMessageDialog(null, s.getLogInId() + "님 감사합니다.");
		s.setLogInId("");
		logIn();
	}
	
}
