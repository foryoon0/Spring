package spring.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

@Controller
public class MemberDetailController {
	
	private MemberDao dao;
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/member/detail/{id}")
	public String detail(@PathVariable("id") Long memberId, Model model) {
		
		Member m = dao.selectById(memberId);
		
		if(m==null) {
			throw new MemberNotFoundException();
		}
		
		model.addAttribute("member",m);
		
		return "member/memberDetail";
	}
	
	/*
	 * // 예외처리 코드는 예외가 발생할 컨트롤러에 작성
	 * 
	 * @ExceptionHandler(TypeMismatchException.class) // id에 숫자가 아닌 값 public String
	 * handlerTypeMismatchException(TypeMismatchException e) { return
	 * "member/invalidate"; }
	 * 
	 * @ExceptionHandler(MemberNotFoundException.class) // id 존재하지 않는 번호 public
	 * String handlerMemberNotFoundException(MemberNotFoundException e) { return
	 * "member/noMember"; }
	 */
}
