<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function check(f){
	if(f.title.value == ''){alert("제목을 입력하세요."); return false;}
	else{
		if(f.title.value.length > 30){
			alert("제목은 30글자 이내로 작성해야 합니다."); return false;
		}
	}
	if(f.price.value == ''){
		alert("가격를 입력하세요."); return false;
	}
	if(f.password.value == ''){
		alert("암호를 입력하세요. 암호는 수정 및 삭제에 필요합니다."); return false;
	}

	if(f.content.value == ''){alert("내용을 작성하세요"); return false;}
	var r = confirm("게시글을 올리시겠습니까?");
	if(r == false) return false;
}
</script>
<div align="center">
<h2>거래글 작성</h2>
<form:form action="../write/tradeupload.html" modelAttribute="tradeBBS" 
method="post" enctype="multipart/form-data" 
onSubmit="return check(this)"><!-- input type="file"때문에 post해야함 -->
<form:hidden path="order_no" value="${tradeBBS.order_no +1 }"/>
	<c:if test="${ ! empty tradeBBS.group_id }">
		<form:hidden path="group_id" value="${tradeBBS.group_id }"/>
	</c:if>
	<c:if test="${ ! empty tradeBBS.parent_id }">
		<form:hidden path="parent_id" value="${tradeBBS.parent_id }"/>
	</c:if>
											<!-- tradeBBS에서 가져옴 -->
<table>
	<tr><th>제목</th><td><form:input path="title" value="${title }"/></td></tr>	
	<tr><th>가격</th><td><form:input path="price"/></td></tr>	
	<tr><th>암호</th><td><form:password path="password"/></td></tr>	
	<tr><th>이미지</th><td><input type="file" name="IMAGENAME"></td></tr>	
	<tr><th>글내용</th>
	<td><form:textarea path="content" rows="8" cols="40"/></td></tr>	
	<tr><td colspan="2" align="center">
		<input type="submit" value="글올리기">
		<input type="reset" value="취소">
	</td></tr>	
</table>
</form:form>
</div>
</body>
</html>