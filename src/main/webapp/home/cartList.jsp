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
<c:if test="${cart == null }">
	<h2 align="center">찜한 게시물이 없습니다</h2>
</c:if>

<c:if test="${!empty cart }">
<div align="center" class="body">
<h2>찜목록</h2>
<div class="cart">
<table border="1">
	
	<tr><th>게시글제목</th><th>희망구매갯수</th><th>개당가격</th></tr>
	<c:forEach items="${cart.itemList }" var="itemSet"><!-- cart 는 cartController에서 itemList Cart에서 가져옴 -->
	
	<tr><td><a href="javascript:goDetail(${itemSet.item.writing_id })">${itemSet.item.title }</a></td>
		<td>${itemSet.quantity }</td>
		<td>${itemSet.item.price }</td>
	</tr>	
	</c:forEach>
	<tr><td colspan="3" align="center"><a href="javascript:cartClear()">장바구니 비우기</a></td></tr>
</table>
</div><br/>${message }<br/><br/><!-- cartController에서가져옴 -->
</div>
</c:if>
<form name="move" method="post">
	<input type="hidden" name="pid"/>
</form>
<script type="text/javascript">
function cartClear(){
	document.move.action="../read/cartClear.html";
	document.move.submit();
}

function goDetail(id){
	document.move.action="../read/tradeRead.html";
	document.move.pid.value = id;
	document.move.submit();
}

</script>
</body>
</html>