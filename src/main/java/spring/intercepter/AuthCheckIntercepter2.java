package spring.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthCheckIntercepter2 extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러가 수행되기 전 구동되는 기능 => 로그인 인증
		
		HttpSession session = request.getSession();
		
		if(session != null) {
			Object auth = session.getAttribute("authInfo");
			if(auth != null) {
				return true;
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/login");
		return false;
	}
	
}
