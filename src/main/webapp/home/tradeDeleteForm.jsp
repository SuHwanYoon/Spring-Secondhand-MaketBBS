<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function check(f){
	if(f.password.value == ''){alert("��ȣ�� �Է��ϼ���."); return false;}
	var r = confirm("������ �����Ͻðڽ��ϱ�?");
	if(r == false) return false;
}
</script>
<div align="center">
<form:form action="../read/deleteTradeDo.html" method="post" 
modelAttribute="tradeBBS"  onSubmit="return check(this)">
	<form:hidden path="writing_id" value="${tradeBBS.writing_id }"/>
	<table width="100%">
		<tr><th>�� ��</th><td>${tradeBBS.title }</td></tr>
		<tr><th>�ۼ���</th><td>${tradeBBS.writer_name }</td></tr>
		<tr><th>����</th><td>${tradeBBS.price }��</td></tr>
		<tr><th>�� ȣ</th><td><form:password 
			path="password" size="20"/></td></tr>
		<tr><th>�̹���</th><td><img alt="" 
		src="${pageContext.request.contextPath }/upload/${tradeBBS.image_name }"
			width="350" height="300"></td></tr>
		<tr><td colspan="2"><input type="submit" value="����"/>
			<input type="reset" value="���ư���" 
			onClick="javascript:history.go(-1)"/></td></tr>
	</table>
</form:form>
</div>
</body>
</html>