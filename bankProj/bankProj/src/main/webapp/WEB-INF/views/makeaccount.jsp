<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
/* 	$(function() {
		$("input[name='type']").click(function(e) {
			if($(this).is(':checked') && $(this).val()=='special') {
				$("#grade").removeAttr("disabled")
			} else {
				$("#grade").attr("disabled",true)
			}
		})
	})
 */	
	window.onload = function() {
		let select = document.querySelector("#grade");
		let types = document.querySelectorAll("input[name='type']");
		for(let type of types) {
			type.onclick = function() {
				if(this.checked && this.value=='special') {
					select.disabled = false;
				} else {
					select.disabled = true;
				}
			}
		}
	}
	
	$(function() {
		$("#accDoubleId").click(function(e) {
			e.preventDefault();
			$.ajax({
				url:'accDoubleId',
				type:'post',
				async:true,
				dataType:'text',
				data:{id:$("#id").val()},
				success:function(result) {
					if(result=='true') {
						alert('사용중인 계좌번호입니다')
					} else {
						alert("사용 가능한 계좌번호입니다")
					}
				},
				error:function(err) {
					console.log(err)
				}
			})
		})
		
	})
	
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<form action="makeAccount" method="post">
		<div><h3 class="header">계좌개설</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">계좌번호</div>
				<div class="input"><input type="text" name="id" id="id" style="width:120px"/></div>
				<div class="input">&nbsp;<button id="accDoubleId">중복</button></div>
			</div>
			<div class="row">  
				<div class="title">이름</div>
				<div class="input"><input type="text" name="name"/></div>
			</div>
			<div class="row">  
				<div class="title">입금액</div>
				<div class="input"><input type="text" name="balance"/></div>
			</div>
			<div class="row">  
				<div class="title">종류</div>
				<div class="input">
					<input type="radio" name="type" checked value="normal">일반
					<input type="radio" name="type" value="special">특수
				</div>
			</div>
			<div class="row">  
				<div class="title">등급</div>
				<div class="input">
					<select disabled id="grade" name="grade">
						<option>선택</option>
						<option value="VIP">VIP</option>
						<option value="Gold">Gold</option>
						<option value="Silver">Silver</option>
						<option value="Normal">Normal</option>
					</select>
				</div>
			</div>
			<div>
				<input type="submit" value="개설"/>
			</div>
		</div>
	</form>
</body>
</html>