<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LG사이언스파크</title>
<link rel="stylesheet" href="<c:url value="/resources/css/signUp.css"/>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<c:url value="/resources/js/signUp.js"/>"></script>
</head>
<body>
<div class="signUp">
	<h1>회원가입</h1>
	<form id="signUpForm" action="<c:url value="/member/signUp"/>" method="post">
		<hr>
		<table>
			<colgroup>
				<col style="width: 25%;">
				<col style="width: 75%;">
			</colgroup>
			<tbody>
				<tr>
					<th>사번</th>
					<td><input type="text" id="empNo" name="empNo" placeholder="사번" required maxlength="5" autofocus>*사번을 입력해주세요</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="empPw" name="empPw" placeholder="비밀번호" required>*비밀번호를 입력해주세요</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" id="empPw2" placeholder="비밀번호 확인" required>*비밀번호를 다시 한 번 입력해주세요</td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="empNm" placeholder="이름" required>*이름을 입력해주세요</td>
				</tr>
			</tbody>
		</table>
		<hr>
		<div class="buttonDiv">
			<button type="submit">등록</button>
			<button type="button" id="cancelBtn">취소</button>
		</div>
	</form>
</div>
</body>
</html>