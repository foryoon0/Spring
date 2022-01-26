package spring.service;

import spring.dao.MemberDao;
import spring.exception.IdPasswordNotMatchingException;
import spring.vo.AuthInfo;
import spring.vo.Member;

public class AuthService { // 실제적인 로그인 기능을 담당하는 객체
	
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public AuthInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		
		if(member==null) {
			throw new IdPasswordNotMatchingException();
		}
		if(!member.getPassword().equals(password)) {
			throw new IdPasswordNotMatchingException();
		}
		
		return new AuthInfo(member.getId(),member.getEmail(),member.getName());
	}

}
