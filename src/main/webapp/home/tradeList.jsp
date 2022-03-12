<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form:form action="../write/tradeSearch.html" method="post">
<div align="center">
<c:if test="${tradeList == null }">
	<h3>등록된 게시글이 존재하지 않습니다.</h3>
</c:if>
<c:if test="${tradeList != null }">
<input type="text" name="itemName" placeHolder="글제목검색"/>
	<input type="submit" value="검색"/>
<table width="100%">
	<tr><td align="right">${startRow }~${endRow }/${count }</td></tr>
</table>
<table>
	<tr><th>이미지</th><th>제 목</th><th>가격</th><th>작성자</th><th>작성일</th></tr>
	<c:forEach var="bbs" items="${tradeList }">
	<tr><td><img alt="" src="${pageContext.request.contextPath }/upload/${bbs.image_name }" 
			width="50" height="50"></td><!-- 이미지의 절대경로 찾는 el -->
		<td><a href="javascript:goDetail(${bbs.writing_id })">${bbs.title }</a></td>
		<td>${bbs.price }</td>
		<td>${bbs.writer_name }</td>
		<td>${bbs.register_date }</td>
		<td><a href="javascript:goCart(${bbs.writing_id })">장바구니담기</a></td>
		</tr>
	</c:forEach>
</table>

<c:set var="startPage" 
value="${currentPage - (currentPage%10 == 0 ? 10: (currentPage%10))+1}"/>
<c:set var="endPage" value="${startPage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>
<c:if test="${startPage > 10 }">
	<a href="javascript:goPage(${startPage - 1 })">[이전]</a>
</c:if>
<c:forEach var="pageNo" begin="${startPage }" end="${endPage }">
	<c:if test="${currentPage == pageNo }">
		<font size="6">
	</c:if>
<%-- 	<a href="imageList.do?PAGE_NUM=${pageNo }">${pageNo }</a> --%>
		<a href="javascript:goPage(${pageNo })">${pageNo }</a>
	<c:if test="${currentPage == pageNo }">
		</font>
	</c:if>
</c:forEach>
<c:if test="${endPage < pageCount }">
	<a href="javascript:goPage(${endPage + 1 })">[다음]</a>
</c:if>
</c:if>
</div>
</form:form>
<form name="move" method="post">
	<input type="hidden" name="PAGE_NUM" value="${currentPage }"/>
	<input type="hidden" name="pid"/>
	<input type="hidden" name="quantity" />
</form>
<script type="text/javascript">
function goDetail(id){
	document.move.action="../read/tradeRead.html";
	document.move.pid.value = id;
	document.move.submit();
}
function goCart(code){
// 	var url = "../read/cartCheck.html?WRITING_ID="+document.move.pid.value
// 	window.open(url,"_blank_","width=450,height=200")
	document.move.action="../read/cartCheck.html";
	document.move.pid.value = code;
	document.move.quantity.value = 1;
	document.move.submit();
}
function goPage(page){
	document.move.action="../write/tradeList.html";
	document.move.PAGE_NUM.value=page;
	document.move.submit();
}
</script>
</body>
</html>