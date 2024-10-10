<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LG사이언스파크</title>
<link rel="stylesheet" href="<c:url value="/resources/css/list.css"/>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<%-- <script src="<c:url value="/resources/js/list.js"/>"></script> --%>
<script type="text/javascript">
$(function() {
	$('#facilityType').change(function() {
		var facilityType = $(this).val();
		showFacilities(facilityType);
	});

	$('.facility .facilityName').click(function() {
		var facility = $(this).parents('tr');
		reserve(facility);
	});
	
	$('.updateBtn').click(function() {
		var facilityId = $(this).parents('tr').attr('id');
		location.href = '${contextPath}/update?id=' + facilityId;
	});
	
	$('#reservationForm').submit(function() {
		var totalPrice = parseInt($('#totalPrice').text());
		
		if (totalPrice <= 0) {
			alert('예약할 시설을 선택하세요.');
			return false;
		}
		
		var teamBudget = parseInt($('#teamBudget').val());
		
		if (totalPrice > teamBudget) {
			alert('팀예산을 초과하였습니다.');
			return false;
		}
		
		alert('예약이 완료되었습니다.');
		return true;
	});

	$('#logoutBtn').click(function() {
		location.href = '${contextPath}/member/logout';
	});
	
	function reserve(facility) {
		var reservationDetails = $('.reservationDetails tbody').eq(0);
		var rows = reservationDetails.find('tr');
		
		var facilityId = facility.attr('id');
		
		for (var i = 0; i < rows.length; i++) {
			var rowId = rows.eq(i).attr('id');
			
			if (rowId == facilityId) {
				alert('이미 목록에 있는 시설입니다.');
				return;
			}
		}

		var totalPrice = $('#totalPrice');
		var price = parseInt(facility.find('.price').text());
		
		var facilityName = facility.find('.facilityName').text();
		
		var template = $('#reservationTemplate');
		var row = template.clone();

		row.attr('id', facilityId);
		row.find('.facilityName').text(facilityName);
		row.find('.price').text(price);
		
		var deleteBtn = row.find('button');
		deleteBtn.click(function() {
			var tr = $(this).parents('tr');
			var price = tr.find('.price').text();
			
			totalPrice.text(totalPrice.text() - price);
			tr.remove();
		});
		
		row.removeClass('blind');
		reservationDetails.append(row);

		var tPrice = parseInt(totalPrice.text());
		totalPrice.text(tPrice + price);
	}
	
	function showFacilities(facilityType) {
		$('.facility tbody tr').addClass('blind');
		
		if (facilityType) {
			$('.facility tbody tr.' + facilityType).each(function() {
				$(this).removeClass('blind');
			});
		}
	}
});
</script>
</head>
<body>
<header>
	<div class="leftHalf">
		<img src="<c:url value="resources/img/logo.jpg"/>" class="logo">
	</div>
	<div class="rightHalf">
		<a id="logoutBtn">로그아웃</a>
	</div>
</header>
<hr>
<div class="facility">
	<div class="tableTop">
		<label for="facilityType">시설유형</label>
		<select id="facilityType" autofocus>
			<option value="">선택하세요</option>
			<option value="CR">회의실</option>
			<option value="PR">프로젝트룸</option>
			<option value="HL">강당</option>
		</select>
	</div>
	<table>
		<colgroup>
			<col style="width: 40%;">
			<col style="width: 15%;">
			<col style="width: 15%;">
			<col style="width: 15%;">
			<col style="width: 15%;">
		</colgroup>
		<thead>
			<tr>
				<th>시설명</th>
				<th>시설유형</th>
				<th>이용료</th>
				<th>사용가능시간</th>
				<th></th><!-- Update Button -->
			</tr>
		</thead>
		<tbody>
		<!-- 
		TODO dymanic tr with jstl and el
		 -->
		 	<c:forEach items="${facilityList}" var="facility">
				<tr id="${facility.id}" class="${facility.type} blind">
					<td class="facilityName">${facility.name }</td>
					<td>${facility.typeName }</td>
					<td><span class="price">${facility.price}</span>원</td>
					<td>${facility.time}</td>
					<td><button type="button" class="updateBtn">수정</button></td>
				</tr>
		 	</c:forEach>
		</tbody>
	</table>
</div>
<div class="reservation">
	<div class="tableTop">
		<label for="teamBudget">팀예산</label>
		<input type="text" id="teamBudget" value="${teamBudget }" readonly>
	</div>
	<form id="reservationForm" action="" method="get">
		<div class="reservationDetails">
			<table>
				<colgroup>
					<col style="width: 60%;">
					<col style="width: 20%;">
					<col style="width: 20%;">
				</colgroup>
				<thead>
					<tr>
						<th>시설명</th>
						<th>이용료</th>
						<th></th><!-- Delete Button -->
					</tr>
				</thead>
				<tbody>
					<tr id="reservationTemplate" class="blind">
						<td class="facilityName"></td>
						<td class="price"></td>
						<td><button type="button">삭제</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<table class="reservationSum">
			<colgroup>
				<col style="width: 30%;">
				<col style="width: 70%;">
			</colgroup>
			<tbody>
				<tr>
					<th>총 금액</th>
					<td><span id="totalPrice">0</span>원</td>
				</tr>
				<tr>
					<th>사번</th>
					<td>${empNo}(${empNm})</td>
				</tr>
			</tbody>
		</table>
		<div class="reservationButtonDiv">
			<button type="submit">시설예약</button>
		</div>
	</form>
</div>
</body>
</html>