<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<form action="deposit" method="post">
		<div><h3 class="header">입금</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">계좌번호</div>
				<div class="input"><input type="text" name="id"/></div>
			</div>
			<div class="row">  
				<div class="title">입금액</div>
				<div class="input"><input type="text" name="money"/></div>
			</div>
			<div>
				<input type="submit" value="입금"/>
			</div>
		</div>
	</form>	
</body>
</html>