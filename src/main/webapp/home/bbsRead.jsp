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
	<h3>�������� �ʴ� ���Դϴ�</h3>
</c:if>
<c:if test="${!empty BBS }">
	<h3>�����λ� ��</h3>
	<table>
		<tr><th>�۹�ȣ</th><td>${BBS.seqno }</td></tr>
		<tr><th>�ۼ���</th><td>${BBS.id }</td></tr>
		<tr><th>����</th><td>${BBS.title }</td></tr>
		<tr><th>�ۼ���</th><td>${BBS.register_date }</td></tr>
		<tr><th>����</th>
		<td><textarea rows="5" cols="40">${BBS.content }</textarea></td></tr>
	</table>
</c:if>
</div>
</body>
</html>