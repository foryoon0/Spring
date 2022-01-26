package spring.service;

import java.util.Date;

import spring.dao.MemberDao;
import spring.exception.AlreadyExistingMemberException;
import spring.vo.Member;
import spring.vo.RegisterRequest;

public class MemberRegisterService { // 회원 정보를 저장하기 위한 기능을 담당하는 클래스
	
	private MemberDao memberDao; //= new MemberDao();  //사용할 객체 직접 생성
	
	public MemberRegisterService(MemberDao memberDao) { // 사용할 객체를 외부에서 주입받는 형태
		this.memberDao = memberDao;
	}
	
	public void regist(RegisterRequest req) {
		// 이미 가입된 계정이 있는가?
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member !=null) {
			throw new AlreadyExistingMemberException("이메일 중복 : "+req.getEmail());
		}
		// 가입된 계정이 없다면 Member객체로 옮겨 담아서 
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), new Date());
		
		memberDao.insert(newMember); //회원 가입 진행
		
	}
}
