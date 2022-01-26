<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="change.pwd.title"/></title>
</head>
<body>
<form:form commandName="changePwdCommand">
	<p>
		<spring:message code="currentPassword"/>:<br>
		<form:password path="currentPassword"/>
		<form:errors path="currentPassword"/>
	</p>
	<p>
		<spring:message code="newPassword"/>:<br>
		<form:password path="newPassword"/>
		<form:errors path="newPassword"/>
	</p>
	<p><form:errors /></p>
	<input type="submit" value="<spring:message code="change.pwd.btn"/>">
</form:form>
</body>
</html>