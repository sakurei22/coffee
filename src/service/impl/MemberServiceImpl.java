package service.impl;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.MemberDto;
import service.MemberService;

//controller와 dao의 중계자
public class MemberServiceImpl implements MemberService {
	MemberDao dao = new MemberDaoImpl();

	@Override
	public boolean selectId(String id) {
		return dao.selectId(id);
	}

	@Override
	public boolean addMember(MemberDto dto) {
		return dao.addMember(dto);
	}

	@Override
	public MemberDto loginMember(String id, String pwd) {
		return dao.loginMember(id, pwd);
	}
}
