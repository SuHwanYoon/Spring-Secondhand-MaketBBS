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
<c:if test="${ empty param.id }">
	<script type="text/javascript">
		alert("�Խñ��� �����Ǿ����ϴ�.");
		location.href="../write/tradeList.html";//������� ����.
	</script>
</c:if>
<c:if test="${ ! empty param.id }">
	<script type="text/javascript">
		alert("��ȣ�� ��ġ���� �ʽ��ϴ�. ��ȣ�� Ȯ���ϼ���.");
		location.href="../read/tradeRead.html?pid="+${param.id};//�󼼺���� ����.
	</script>
</c:if>
</body>
</html>