package spring.survey;

import java.util.List;

public class AnsweredData { //응답 정보  => 커맨드 객체(프론트(html,jsp등)와 서버 컨트롤러와 데이터 교환용)로 사용할 예정
	
	private List<String> responses;
	private Respondent res;
	
	public List<String> getResponses() {
		return responses;
	}
	public void setResponses(List<String> responses) {
		this.responses = responses;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
	
	
}
