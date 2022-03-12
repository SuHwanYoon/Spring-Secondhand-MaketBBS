<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<c:if test="${empty BBS }">
	<h3>존재하지 않는 글입니다</h3>
</c:if>
<c:if test="${!empty BBS }">
	<h3>가입인사 상세</h3>
	<table>
		<tr><th>글번호</th><td>${BBS.seqno }</td></tr>
		<tr><th>작성자</th><td>${BBS.id }</td></tr>
		<tr><th>제목</th><td>${BBS.title }</td></tr>
		<tr><th>작성일</th><td>${BBS.register_date }</td></tr>
		<tr><th>내용</th>
		<td><textarea rows="5" cols="40">${BBS.content }</textarea></td></tr>
	</table>
</c:if>
</div>
</body>
</html>