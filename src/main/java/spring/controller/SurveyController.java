package spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.survey.AnsweredData;
import spring.survey.Question;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private List<Question> createQuestion(){ //설문의 질문을 만드는 메서드
		Question q1 = new Question(
				"당신의 역할은 무엇입니까?",
				Arrays.asList("서버","프론트","풀스택"));
		
		Question q2 = new Question(
				"많이 사용하는 개발 도구는 무엇입니까?",
				Arrays.asList("NetBeans","Eclipse","IntelliJ"));
		
		Question q3 = new Question(
				"하고 싶은 말을 적어 주세요.");
		
		return Arrays.asList(q1,q2,q3);
	}

//	@RequestMapping(method=RequestMethod.GET)
//	public String form(Model model) {
//		
//		//질문을 만들어서 뷰에 보내도록 합니다.
//		 List<Question> questions = createQuestion();
//		 model.addAttribute("questions",questions);
//		 // 뷰에 데이터를 보내기 위한 객체  : Model
//		return "survey/surveyForm";
//	}
//	
	
	// 스프링 컨트롤러의 원형
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView();
		
		List<Question> questions = createQuestion();
		
		mav.addObject("questions",questions); // Model의 역할: 프론트로 보낼 데이터 저장
		mav.setViewName("survey/surveyForm"); // View의 역할 :  프론트로 보낼 뷰페이지 경로
		
		return mav;
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("ansData") AnsweredData data){
		
		System.out.println("리스트 : "+data.getResponses().get(0).toString());
		
		return "survey/submitted";
	}
	
	
}
