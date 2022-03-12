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
function check(){
	if(! confirm("게시물을 올리겠습니까?")){return false;}
}

</script>
<div align="center">
	<h2>가입인사 쓰기</h2>
	<form:form action="../write/write.html" method="post" 
	onsubmit="return check()"
	modelAttribute="bbs"><!-- 객체이름 맨앞글자만 소문자로 -->
	<table>
		<tr>
			<th>제목</th>
			<td><form:input path="title" 
			placeHolder="제목을 입력하세요" size="20"/><br/>
			<font color="red"><form:errors path="title"/></font></td>
		</tr>
		<tr><th>내용</th><td>
		<form:textarea path="content" placeHolder="내용을 입력하세요"
		 rows="6" cols="40"/><br/>
		 <font color="red"><form:errors path="content"/></font>
		</td></tr>	
		<tr><td colspan="2" align="center">
			<input type="submit" value="글올리기">
			<input type="reset" value="취소">
		</td></tr>
	</table>
	</form:form>
</div>
</body>
</html>