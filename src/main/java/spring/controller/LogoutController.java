package spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //세션에 저장된 모든 데이터를 제거
		
		return "redirect:/main";
	}
}
