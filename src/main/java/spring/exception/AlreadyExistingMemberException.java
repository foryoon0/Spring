package spring.exception;

public class AlreadyExistingMemberException extends RuntimeException{
		// 회원 가입할 때 이미 존재한 계정이 있는 경우 예외 발생
	public AlreadyExistingMemberException(String msg) {
		super(msg);
	}
}
