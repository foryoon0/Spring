<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="./resources/style/myStyle.css">
</head>
<body>
	<h2>안녕하세요</h2>
	<c:if test="${empty authInfo}">
		<p>환영합니다.</p>
		<p><a href="<c:url value='/register/step1' />">[회원 가입하기]</a></p>
		<p><a href="<c:url value='/login' />">[로그인]</a></p>
	</c:if>
	<c:if test="${!empty authInfo}">
		<h2>${authInfo.name}님 반갑습니다</h2>
		<p><a href="<c:url value='/edit/changePassword' />">[비밀번호 변경]</a></p>
		<p><a href="<c:url value='/logout' />">[로그 아웃]</a></p>
	</c:if>
	<p><a href="<c:url value='/survey' />">[설문 조사]</a></p>
	<p><a href="<c:url value='/member/list' />">[회원 조회]</a></p>
</body>
</html>