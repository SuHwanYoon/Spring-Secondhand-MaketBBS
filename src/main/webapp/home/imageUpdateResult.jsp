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
<c:if test="${ ! empty param.seqno }">
	<script type="text/javascript">
		alert("이미지 게시글이 수정되었습니다.");
		location.href="../write/imageList.html?seqno="+${param.seqno};//목록으로 간다.
	</script>
</c:if>
<c:if test="${ ! empty param.id }">
	<script type="text/javascript">
		alert("암호가 일치하지 않습니다.");
		location.href="../read/imageRead.html?pid="+${param.id};//상세보기로 간다.
	</script>
</c:if>
</body>
</html>














