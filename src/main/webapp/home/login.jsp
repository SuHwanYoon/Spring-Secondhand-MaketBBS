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

<div align="center">
<c:if test="${MSG != null }">
	<font color="red">${MSG }</font>
</c:if>

<div align="right"><a href="../userentry/userentry.html">�����ϱ�</a></div>
<form:form action="../login/home.html" method="post" 
modelAttribute="loginuser">
���̵�: <form:input path="id" size="12" 
placeHolder="������ �Է��ϼ���"/><font color="red">
<form:errors path="id"/></font><br/>
��ȣ: <form:password path="pwd" size="12" 
placeHolder="��ȣ�� �Է��ϼ���"/><font color="red">
<form:errors path="pwd"/></font><br/>
<input type="submit" value="�α���">
<input type="reset" value="���">

</form:form>
</div>
</body>
</html>