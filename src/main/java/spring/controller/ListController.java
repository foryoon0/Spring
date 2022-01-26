package spring.controller;

import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.ListCommand;
import spring.vo.Member;

@Controller
public class ListController {
	
	private MemberDao dao;

	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/member/list")
	public String list(@ModelAttribute("listCommand") ListCommand listCommand,Errors errors,Model model) {
		
		if(errors.hasErrors()) {
			return "member/memberList"; // 자동으로 형변환 오류
		}
		
		if(listCommand.getFrom()!=null && listCommand.getTo() !=null) {	
			List<Member> list = dao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());	
			model.addAttribute("members",list);
		}

		return "member/memberList";
	}

	
}






