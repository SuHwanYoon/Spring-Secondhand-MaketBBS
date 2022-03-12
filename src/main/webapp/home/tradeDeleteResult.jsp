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
		alert("게시글이 삭제되었습니다.");
		location.href="../write/tradeList.html";//목록으로 간다.
	</script>
</c:if>
<c:if test="${ ! empty param.id }">
	<script type="text/javascript">
		alert("암호가 일치하지 않습니다. 암호를 확인하세요.");
		location.href="../read/tradeRead.html?pid="+${param.id};//상세보기로 간다.
	</script>
</c:if>
</body>
</html>