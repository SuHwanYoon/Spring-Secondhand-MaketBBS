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
<h2 align="center">���� �ߺ� Ȯ��</h2>
<form action="../userentry/idCheck.html" name="frm"><!--action:�� ���������ָ� �����η� ���� -->
	<input type="hidden" name="OK" value="${ID }">
	����: <input type="text" name="USER_ID" value="${ID }">
	<input type="submit" value="�ߺ��˻�"><br/>
</form>
<c:if test="${ DUP == 'YES' }">
<script type="text/javascript">
	opener.document.frm.id.value = '';
</script>
${ID }�� �̹� ������Դϴ�
</c:if>
<c:if test="${ DUP == 'NO'}">
${ID }�� ��� �����մϴ�
<input type="button" value="���" onClick="idOK()">
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