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
function check(){
	if(! confirm("�Խù��� �ø��ڽ��ϱ�?")){return false;}
}

</script>
<div align="center">
	<h2>�����λ� ����</h2>
	<form:form action="../write/write.html" method="post" 
	onsubmit="return check()"
	modelAttribute="bbs"><!-- ��ü�̸� �Ǿձ��ڸ� �ҹ��ڷ� -->
	<table>
		<tr>
			<th>����</th>
			<td><form:input path="title" 
			placeHolder="������ �Է��ϼ���" size="20"/><br/>
			<font color="red"><form:errors path="title"/></font></td>
		</tr>
		<tr><th>����</th><td>
		<form:textarea path="content" placeHolder="������ �Է��ϼ���"
		 rows="6" cols="40"/><br/>
		 <font color="red"><form:errors path="content"/></font>
		</td></tr>	
		<tr><td colspan="2" align="center">
			<input type="submit" value="�ۿø���">
			<input type="reset" value="���">
		</td></tr>
	</table>
	</form:form>
</div>
</body>
</html>