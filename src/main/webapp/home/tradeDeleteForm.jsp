<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	if(f.password.value == ''){alert("암호를 입력하세요."); return false;}
	var r = confirm("정말로 삭제하시겠습니까?");
	if(r == false) return false;
}
</script>
<div align="center">
<form:form action="../read/deleteTradeDo.html" method="post" 
modelAttribute="tradeBBS"  onSubmit="return check(this)">
	<form:hidden path="writing_id" value="${tradeBBS.writing_id }"/>
	<table width="100%">
		<tr><th>제 목</th><td>${tradeBBS.title }</td></tr>
		<tr><th>작성자</th><td>${tradeBBS.writer_name }</td></tr>
		<tr><th>가격</th><td>${tradeBBS.price }원</td></tr>
		<tr><th>암 호</th><td><form:password 
			path="password" size="20"/></td></tr>
		<tr><th>이미지</th><td><img alt="" 
		src="${pageContext.request.contextPath }/upload/${tradeBBS.image_name }"
			width="350" height="300"></td></tr>
		<tr><td colspan="2"><input type="submit" value="삭제"/>
			<input type="reset" value="돌아가기" 
			onClick="javascript:history.go(-1)"/></td></tr>
	</table>
</form:form>
</div>
</body>
</html>