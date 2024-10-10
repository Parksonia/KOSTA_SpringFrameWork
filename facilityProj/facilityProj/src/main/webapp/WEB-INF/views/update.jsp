<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LG사이언스파크</title>
<link rel="stylesheet" href="<c:url value="/resources/css/update.css"/>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<c:url value="/resources/js/update.js"/>"></script>

</head>
<body>
<div class="signUp">
	<h1>시설수정</h1>
	<hr>
	<form id="updateForm" action="<c:url value="/facility/update"/>" method="post">
		<input type="hidden" name="id" value="${facility.id }"><!-- TODO value -->
		<table>
			<colgroup>
				<col style="width: 25%;">
				<col style="width: 75%;">
			</colgroup>
			<tbody>
				<tr>
					<th>시설유형</th>
					<td><input type="text" value="${facility.typeName }" readonly></td>
				</tr>
				<tr>
					<th>시설명</th>
					<td><input type="text" name="name" value="${facility.name }" placeholder="시설명" required>*시설명을 입력해주세요</td>
				</tr>
				<tr>
					<th>이용료</th>
					<td><input type="text" name="price" value="${facility.price }" placeholder="이용료" required>*이용료를 입력해주세요</td><!-- TODO value -->
				</tr>
				<tr>
					<th>사용가능시간</th>
					<td><input type="text" value="${facility.time }" readonly></td><!-- TODO value -->
				</tr>
				<tr>
					<th>ETC</th>
					<td><input type="text" name="etc" value="${facility.etc}" placeholder="etc" class="input-large"></td><!-- TODO value -->
				</tr>
			</tbody>
		</table>
		<hr>
		<div class="buttonDiv">
			<button type="submit">수정</button>
			<button type="button" id="cancelBtn">취소</button>
		</div>
	</form>
</div>
</body>
</html>