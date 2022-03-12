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
	<c:choose>
	<c:when test="${NOLOGIN != null }">
		<h3>로그인 되지 않았습니다. 계정과 암호를 확인하세요.</h3>
	</c:when>
	<c:when test="${LOGIN != null }">
		<h3>로그인 되었습니다. 환영합니다~~~${sessionScope.LOGINUSER.id }님</h3>
	</c:when>
	<c:when test="${CARTLOGIN == 'YES' }">
		<script type="text/javascript">
		alert("로그인 되었습니다.");
		self.close(); 
		opener.window.location.reload();
		</script>
	</c:when>
		<c:when test="${CARTNOLOGIN == 'YES' }">
		<script type="text/javascript">
		alert("로그인되지 않았습니다.");
		self.close();
		</script>
	</c:when>
</c:choose>	
</div>
</body>
</html>