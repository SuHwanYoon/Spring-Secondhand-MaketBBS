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
<h2 align="center">계정 중복 확인</h2>
<form action="../userentry/idCheck.html" name="frm"><!--action:에 지정안해주면 현재경로로 유지 -->
	<input type="hidden" name="OK" value="${ID }">
	계정: <input type="text" name="USER_ID" value="${ID }">
	<input type="submit" value="중복검사"><br/>
</form>
<c:if test="${ DUP == 'YES' }">
<script type="text/javascript">
	opener.document.frm.id.value = '';
</script>
${ID }는 이미 사용중입니다
</c:if>
<c:if test="${ DUP == 'NO'}">
${ID }는 사용 가능합니다
<input type="button" value="사용" onClick="idOK()">
</c:if>
<script type="text/javascript">
function idOK(){
	opener.document.frm.id.value = document.frm.OK.value;
	opener.document.frm.idChecked.value = "OK";
	opener.document.getElementById("ID").readOnly= true;
	self.close();
}

</script>
</body>
</html>