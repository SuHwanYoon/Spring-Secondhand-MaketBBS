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

<c:if test="${ empty USERUPDATE }">
	<script type="text/javascript">
	alert("������ ������� �ʾҽ��ϴ�.");
	</script>
</c:if>
<c:if test="${ ! empty USERUPDATE }">
	<script type="text/javascript">
		alert("������ ����Ǿ����ϴ�.");
		location.href="../userentry/userModifyForm.html";
	</script>

</c:if>
</body>
</html>