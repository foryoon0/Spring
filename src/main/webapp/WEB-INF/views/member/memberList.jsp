<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code='selectMemberList'/></title>
</head>
<body>
	<form:form commandName="listCommand">
		<p>
			<label>from: <form:input path="from"/> </label>
			<form:errors path="from"/>
			~
			<label>to: <form:input path="to"/> </label>
			<form:errors path="to"/>
			<input type="submit" value="<spring:message code='selectMemberList.check'/>">
		</p>
	</form:form>
	<c:if test="${!empty members}">
		<table border=1>
			<tr>
				<th>아이디</th>
				<th>이메일</th>
				<th>이름</th>
			</tr>
			<c:forEach var="member" items="${members}">
				<tr>
					<td>${member.id}</td>
					<td>
						<a href="<c:url value='/member/detail/${member.id}' />"> ${member.email} </a>
						
					</td>
					<td>${member.name}</td>
				</tr>
			</c:forEach>
		</table>
	
	</c:if>
	

</body>
</html>