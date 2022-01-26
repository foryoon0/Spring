package spring.common;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.exception.MemberNotFoundException;

@ControllerAdvice("spring")
public class CommonExceptionHandler {   //예외 처리 클래스
	
	// 예외처리 코드는 예외가 발생할 컨트롤러에 작성
	@ExceptionHandler(TypeMismatchException.class)  // id에 숫자가 아닌 값
	public String handlerTypeMismatchException(TypeMismatchException e) {
		return "member/invalidate";
	}
	
	@ExceptionHandler(MemberNotFoundException.class) // id 존재하지 않는 번호
	public String handlerMemberNotFoundException(MemberNotFoundException e) {
		return "member/noMember";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handlerRuntimeException() {
		return "error/commonException";
	}
	
	
}
