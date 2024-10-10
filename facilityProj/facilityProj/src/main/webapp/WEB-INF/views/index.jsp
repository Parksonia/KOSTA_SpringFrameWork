<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LG사이언스파크</title>
<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<%-- <script src="<c:url value="/resources/js/index.js"/>"></script> --%>
<script>
$(function() {
	$('#signUpBtn').click(function() {
		location.href = '${contextPath}/member/signUp';
	});
}); 
</script>
</head>
<body>
<div class="login">
	<form id="loginForm" action="<c:url value="/member/login"/>" method="post">
		<input type="text" name="empNo" placeholder="사번" required maxlength="5" autofocus>
		<input type="password" name="empPw" placeholder="비밀번호" required>
		<button type="submit">로그인</button>
		<button type="button" id="signUpBtn">회원가입</button>
	</form>
</div>
</body>
</html>