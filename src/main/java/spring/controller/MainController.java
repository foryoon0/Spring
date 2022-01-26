package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/main")   //역할 : main경로로 왔을 때 main.jsp 연결
	public String main() {
		return "main"; // 응답 경로  : /WEB-INF/views/main.jsp 
	}
}
