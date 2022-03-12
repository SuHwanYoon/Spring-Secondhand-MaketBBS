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
<c:if test="${ empty TRADE }"><!-- TRADE ReadController���� ������ -->
	<h3>�������� �ʴ� ���Դϴ�.</h3>
</c:if>
<c:if test="${ ! empty TRADE}">
<table width="100%">
	<tr><th width="300px">�� ��</th><td>${TRADE.title }</td></tr>
	<tr><th width="300px">�ۼ���</th><td>${TRADE.writer_name }</td></tr>
	<tr><th width="300px">����</th><td>${TRADE.price }��</td></tr>
	<tr><td colspan="2"><img alt="" src="${pageContext.request.contextPath }/upload/${TRADE.image_name }"
			width="350" height="300"></td></tr>
	<tr><th width="300px">�� ��</th><td><textarea rows="4" cols="50">${TRADE.content }</textarea>
		</td></tr>
	<tr><td colspan="2" align="center">
		<a href="javascript:goReply()">[���]</a>
		<a href="javascript:goModify()">[����]</a>
		<a href="javascript:goDelete()">[����]</a>
		<a href="../write/tradeList.html">[���]</a></td></tr>
</table>
</c:if>
</div>
<form name="move" method="post">
	<input type="hidden" name="id" value="${TRADE.writing_id }"/>
	<input type="hidden" name="parentid"
				value="${TRADE.writing_id }"/>
	<input type="hidden" name="groupid"
				value="${TRADE.group_id }"/>
</form>
<script type="text/javascript">
function goReply(){
	document.move.action="../write/replyTradeForm.html";
	document.move.submit();
}
function goModify(){
	document.move.action="../read/updateTrade.html";
	document.move.submit();
}
function goDelete(){
	document.move.action="../read/deleteTrade.html";
	document.move.submit();
}
</script>
</body>
</html>