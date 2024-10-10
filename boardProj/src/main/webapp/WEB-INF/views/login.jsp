<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<% String message = (String)request.getAttribute("message"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
</head>
<body>

	<% if(message !=null){  %>
	<h1><%= message %></h1>
	<%} %>
	<form action="login" method="post">
		<div><h3 class="header">로그인</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">아이디</div>
				<div class="input"><input type="text" name="id"/></div>
			</div>
			<div class="row">  
				<div class="title">비밀번호</div>
				<div class="input"><input type="text" name="password"/></div>
			</div>
			<div class="row">  
				<input type="checkbox" name="type" class="title"><b>자동로그인</b>
			</div>
			<div style="display:flex;align-items: center; flex-direction: column;">
				<input type="submit" value="로그인"/><br>
				<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=d1e55a57c615ed931fe94c2d77ef5a3c
									&redirect_uri=http://localhost:8080/board/kakao&response_type=code">
				<img src='<c:url value="resources/image/kakao_login_medium.png"/>'>
				</a><br>
		
			<a href="https://nid.naver.com/oauth2.0/authorize?client_id=cN5KhLKCmZQ3KCGoyauD&redirect_uri=http://localhost:8080/board/naver&response_type=code&state=1357">
              <img src='<c:url value="resources/image/naver로그인btn.png"/>' style="width:90px; height:45px;"></a>
			</div>
		</div>
		
	</form>
</body>
</html>