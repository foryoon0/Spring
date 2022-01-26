<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 조사</title>
</head>
<body>
<h2>설문 조사</h2>
<form method="POST">
<!-- 	<p>
		1. 당신의 역할은? <br>
		<label> <input type="radio" name="responses[0]" value="서버">서버 개발자 </label>
		<label> <input type="radio" name="responses[0]" value="프론트">프론트 개발자 </label>
		<label> <input type="radio" name="responses[0]" value="풀스택">풀스택 개발자 </label>
	</p>
	<p>
		2. 가장 많이 사용하는 개발도구는? <br>
		<label> <input type="radio" name="responses[1]" value="Eclipse">Eclipse </label>
		<label> <input type="radio" name="responses[1]" value="IntelliJ">IntelliJ </label>
		<label> <input type="radio" name="responses[1]" value="NetBeans">NetBeans </label>
	</p>
	<p>
		3. 하고 싶은 말<br>
		<input type="text" name="responses[2]">
	</p> -->
	<c:forEach items="${questions}" var="q" varStatus="s">
		<p>
		 	${s.index+1}. ${q.title}	<br>
			<c:if test="${q.choice}">
				<c:forEach items="${q.option}" var="option">
					<label> 
						<input type="radio" name="responses[${s.index}]" value="${option}">${option}
					</label>
				</c:forEach>
			</c:if>
			<c:if test="${!q.choice}">
				<input type="text" name="responses[${s.index}]" >
			</c:if>
		</p>
	</c:forEach>
	<p>
		<label>응답자 위치:
			<input type="text" name="res.location">
		</label>
	</p>
	<p>
		<label>응답자 나이:
			<input type="text" name="res.age">
		</label>
	</p>
	<input type="submit" value="전송">
</form>
</body>
</html>