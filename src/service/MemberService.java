package service;

import dto.MemberDto;

public interface MemberService {
	
	public boolean selectId(String id); //prototype 메소드의 사양을 한꺼번에 확인 가능
	
	public boolean addMember(MemberDto dto);
	
	public MemberDto loginMember(String id, String pwd);
}
