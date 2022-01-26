package spring.vo;

public class LoginCommand { //클라  <-> 컨 : 커맨드 객체 : 생성자 만들면 오류
	
	private String email;
	private String password;
	private boolean rememberEmail;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberEmail() {
		return rememberEmail;
	}
	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}
	
	
	

}
