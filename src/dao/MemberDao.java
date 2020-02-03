package dao;

import dto.MemberDto;

//db랑 대화
public interface MemberDao {
	
	public boolean selectId(String id);
	
	public boolean addMember(MemberDto dto);
	
	public MemberDto loginMember(String id, String pwd);
	
}
