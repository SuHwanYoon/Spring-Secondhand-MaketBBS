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
<div align="center">
<form:form action="../read/updateImageDo.html" method="post" encType="multipart/form-data"
		onSubmit="return check(this)" modelAttribute="imageBBS">
<form:hidden path="writing_id" value="${imageBBS.writing_id }"/>
<table width="100%">
	<tr><th>제 목</th><td><form:input path="title" size="20"
			value="${imageBBS.title }"/></td></tr>
	<tr><th>이메일</th><td><form:input path="email" size="20"
			value="${imageBBS.email }"/></td></tr>
	<tr><th>암 호</th><td><form:password path="password" size="20"/>
		</td></tr>
	<tr><th>이미지</th><td><input type="file" name="IMAGENAME"/><br/>
		<img alt="" src="${pageContext.request.contextPath }/upload/${imageBBS.image_name }" 
				width="350" height="300"></td></tr>
	<tr><th>글내용</th><td><form:textarea rows="8" cols="40"
		path="content" value="${imageBBS.content }"></form:textarea></td></tr>
	<tr><td colspan="2" align="center"><input type="submit" value="수정"/>
		<input type="reset" value="취소"/></td></tr>
</table>
</form:form>
</div>
<script type="text/javascript">
function check(f){
	if(f.title.value == '') {alert("제목을 입력하세요."); return false;}
	if(f.password.value == ''){alert("암호를 입력하세요."); return false;}
	if(f.content.value == ''){alert("글 내용을 입력하세요."); return false;}
	var r = confirm("정말로 변경하시겠습니까?");
	if(r == false) return false;
}
</script>
</body>
</html>












