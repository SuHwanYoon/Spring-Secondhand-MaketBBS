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
		<h3>�α��� ���� �ʾҽ��ϴ�. ������ ��ȣ�� Ȯ���ϼ���.</h3>
	</c:when>
	<c:when test="${LOGIN != null }">
		<h3>�α��� �Ǿ����ϴ�. ȯ���մϴ�~~~${sessionScope.LOGINUSER.id }��</h3>
	</c:when>
	<c:when test="${CARTLOGIN == 'YES' }">
		<script type="text/javascript">
		alert("�α��� �Ǿ����ϴ�.");
		self.close(); 
		opener.window.location.reload();
		</script>
	</c:when>
		<c:when test="${CARTNOLOGIN == 'YES' }">
		<script type="text/javascript">
		alert("�α��ε��� �ʾҽ��ϴ�.");
		self.close();
		</script>
	</c:when>
</c:choose>	
</div>
</body>
</html>