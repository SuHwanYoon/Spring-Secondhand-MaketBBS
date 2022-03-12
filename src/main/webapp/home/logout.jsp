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
<form action="../logout/logout.html">
환영합니다! ${sessionScope.LOGINUSER.id }님<br/>
<input type="submit" value="로그아웃">
</form>
</div>
</body>
</html>