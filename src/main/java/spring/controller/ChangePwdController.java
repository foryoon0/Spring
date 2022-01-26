package spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.ChangePasswordService;
import spring.validator.ChangePwdCommandValidator;
import spring.vo.AuthInfo;
import spring.vo.ChangePwdCommand;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {

	private ChangePasswordService changePasswordSvc;

	public ChangePwdController(ChangePasswordService changePasswordSvc) {
		this.changePasswordSvc = changePasswordSvc;
	}
	
	// 폼페이지에 빈 커맨드 객체가 필요하다. (form:form태그의 commandName 때문에)
	// 1. Model객체를 이용하는 방법
//	@RequestMapping(method=RequestMethod.GET)
//	public String form(Model model) {
//		model.addAttribute("changePwdCommand",new ChangePwdCommand());
//		return "edit/changePwdForm";
//	}
	
	// 2. ModelAttribute 어노테이션를 이용하는 방법
//	@RequestMapping(method=RequestMethod.GET)
//	public String form(@ModelAttribute("changePwdCommand")ChangePwdCommand changePwdCommand ) {
//		return "edit/changePwdForm";
//	}	
	
	// 3. Post하고 같은 커맨드 객체를 사용하는 경우 model생략 가능
	@RequestMapping(method=RequestMethod.GET)
	public String form(ChangePwdCommand changePwdCommand,HttpSession session ) {
//		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
//		
//		if(authInfo==null) {
//			return "redirect:/login";  // 로그인한 상태가 아니라면 로그인 페이지로 이동
//		}
		
		return "edit/changePwdForm";
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(ChangePwdCommand changePwdCommand, Errors errors, HttpSession session) {
		// 1. 입력 값 검증
		new ChangePwdCommandValidator().validate(changePwdCommand, errors);
		
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		try {
			changePasswordSvc.changepassword(
					authInfo.getEmail(), 
					changePwdCommand.getCurrentPassword(), 
					changePwdCommand.getNewPassword());
			
			return "edit/changePwd";
		}catch(IdPasswordNotMatchingException e) { // 저장된 비밀번호와 입력한 기존 비밀번호다를 경우
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
	
}
