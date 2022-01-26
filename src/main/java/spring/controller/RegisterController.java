package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.exception.AlreadyExistingMemberException;
import spring.service.MemberRegisterService;
import spring.validator.RegisterRequestValidator;
import spring.vo.RegisterRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private MemberRegisterService  memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

		@RequestMapping("/step1")  //클라이언트의 요청 경로  : http://localhost:8085/ex00/register/step1
		public String handlerStep1() {
			return "register/step1";  	// 응답 경로  : /WEB-INF/views/register/step1.jsp 
		}
		
//		@RequestMapping(value="/register/step2", method=RequestMethod.POST)
//		public String handlerStep2(HttpServletRequest request) {
//			// 전달 받은 데이터 읽어오기
//			// Request객체를 통해서 데이터를 읽어오기
//			String agree = request.getParameter("agree");
//			
//			if(agree == null || !agree.equals("true")) { //동의를 하지 않은 경우
//				return "register/step1"; 
//			}
//			return "register/step2";
//		}
		
		@RequestMapping(value="/step2", method=RequestMethod.POST)
		public String handlerStep2(@RequestParam(value="agree",defaultValue="false") Boolean agree,Model model) {
			// 전달 받은 데이터 읽어오기
			// RequestParam 어노테이션 기능을 이용하는 방법 제공		
			if(!agree) { //동의를 하지 않은 경우
				return "redirect:/register/step1";
			}
			// 빈 formData라는 객체를 넘겨주어야 한다. => 스프링커스텀 폼태그가 에러가 안남
			model.addAttribute("formData",new RegisterRequest());
			
			return "register/step2";
		}
		
		@RequestMapping(value="/step2", method=RequestMethod.GET)
		public String handerStep2Get() {
			return "redirect:/register/step1"; 
		}
		
		@RequestMapping(value="/step3", method=RequestMethod.GET)
		public String handerStep3Get() {
			return "redirect:/register/step1"; 
		}
		
		//-------------------------------------------------------------------
//		@RequestMapping(value="/step3", method=RequestMethod.POST)
//		public String handlerStep3(
//				@RequestParam(value="email")String email,
//				@RequestParam(value="name")String name,
//				@RequestParam(value="password")String password,
//				@RequestParam(value="confirmPassword")String confirmPassword) {
//			// 4개의 데이터(이메일, 이름, 비밀번호, 비밀번호 확인)
//			// 1. Request.getParameter();
//			// 2. @RequestParam()
//			return "register/step3";
//		}
		
		// 여러 데이터를 한꺼번에 전달 받는 방식 => 커맨드 객체 => 매개값으로 전달 => 응답페이지에 값을 전달
		@RequestMapping(value="/step3", method=RequestMethod.POST)
		public String handlerStep3(@ModelAttribute("formData")RegisterRequest regReq,Errors errors) {
			// 넘어온 커맨드 객체를 검증해야 한다.
			new RegisterRequestValidator().validate(regReq, errors);
			
			if(errors.hasErrors()) {  // 에러가 하나라도 발견이 되었다면
				return "register/step2";
			}
			
			
			try {
				// MemberRegisterService객체 안에 메서드 호출 =>의존 주입
				memberRegisterService.regist(regReq);
				return "register/step3";
			}catch(AlreadyExistingMemberException e) {
				// 이미 회원이 존재하는 상태 =>  에러 
				errors.rejectValue("email", "duplicate");
				return "register/step2";
			}

		}
		
	}
//step2.jsp ====================> controller   ==============================>step3.jsp
//             [커맨드객체]		       			  [Model객체(커맨드객체와 이름같음)]
//			RegisterRequest regReq     ===>          RegisterRequest regReq
//									   ====>    Model객체에 이름을 지정해서 보내는 방법
//	  												@ModelAttribute("이름")
//
//												=====================>step2.jsp
//	   											  [Model객체(커맨드객체와 이름같음)]

