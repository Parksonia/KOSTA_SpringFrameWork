<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 등록</title>
<style type="text/css">
h2, #commandCell {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

.td_left {
	background: orange;
}

.td_right {
	background: skyblue;
}
</style>

<script>
	//onchage event
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	function readDFile(input) {
		if(input.files && input.files[0]) {
			document.getElementById('dFileName').innerText = input.files[0].name;
		}	
	}
	
	
</script>
</head>


<body>
	<h2>게시판 글 수정</h2>
	<!-- 사진 업로드 하려면 cos.jar 라이브러리 추가해야함   -->
	<form action=modifyboard method="post" enctype="multipart/form-data">
		<!--enctype="multipart/form-data": form으로 사진데이터를 제출하기 위해 꼭 지정해줘야함  -->
		<input type="hidden" name ="num" value="${board.num }">
		<table>
			<tr>
				<td class="td_left"><label for="writer">글쓴이</label></td>
				<td class="td_right"><input type="text" name="writer" id="writer" value="${board.writer }" readonly="readonly"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="subject">제목</label></td>
				<td class="td_right"><input type="text" name="subject" id="subject" required="required" value="${board.subject }"></td>
				<!-- "required" : 입력하지 않으면 submit이 안된다 -->
			</tr>
			<tr>
				<td class="td_left"><label for="content">내용</label></td>
				<td class="td_right"><textarea id="content" name="content" cols="40" rows="15" >${board.content}</textarea></td>
			</tr>

			<tr>
				<td class="td_left"><label >이미지 파일 첨부</label></td>
				<td class="td_right" onclick="document.getElementById('file').click();">
				<c:choose>
					<c:when test="${board.filename eq null}">
						<img src="<c:url value="resources/image/plus.png"/>" width="100px" id="preview">
					</c:when>
					<c:otherwise>
						<img src="image/${board.filename}" width="100px" id="preview">
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td class="td_left"><label >파일 첨부</label></td>
				<td class="td_right" onclick="document.getElementById('dfile').click();">
				<span id="dFileName">${board.dfilename}</span>
			</tr>			
		</table>
		<input type="file" style="display:none" name="file" id="file" accept="image/*" onchange="readURL(this);">
		 <input type="file" style="display:none" name="dfile" id="dfile" onchange="readDFile(this);"> 		
		<div id="commandCell">
			<input type="submit" value="수정">&nbsp;&nbsp; <input type="reset" value="다시쓰기">
		</div>
	</form>
</body>
</html>