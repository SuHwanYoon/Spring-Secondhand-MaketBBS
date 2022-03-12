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
	alert("정보가 변경되지 않았습니다.");
	</script>
</c:if>
<c:if test="${ ! empty USERUPDATE }">
	<script type="text/javascript">
		alert("정보가 변경되었습니다.");
		location.href="../userentry/userModifyForm.html";
	</script>

</c:if>
</body>
</html>