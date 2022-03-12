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
<script type="text/javascript">
function check(f){
	if(f.title.value == ''){alert("������ �Է��ϼ���."); return false;}
	else{
		if(f.title.value.length > 30){
			alert("������ 30���� �̳��� �ۼ��ؾ� �մϴ�."); return false;
		}
	}
	if(f.price.value == ''){
		alert("���ݸ� �Է��ϼ���."); return false;
	}
	if(f.password.value == ''){
		alert("��ȣ�� �Է��ϼ���. ��ȣ�� ���� �� ������ �ʿ��մϴ�."); return false;
	}

	if(f.content.value == ''){alert("������ �ۼ��ϼ���"); return false;}
	var r = confirm("�Խñ��� �ø��ðڽ��ϱ�?");
	if(r == false) return false;
}
</script>
<div align="center">
<h2>�ŷ��� �ۼ�</h2>
<form:form action="../write/tradeupload.html" modelAttribute="tradeBBS" 
method="post" enctype="multipart/form-data" 
onSubmit="return check(this)"><!-- input type="file"������ post�ؾ��� -->
<form:hidden path="order_no" value="${tradeBBS.order_no +1 }"/>
	<c:if test="${ ! empty tradeBBS.group_id }">
		<form:hidden path="group_id" value="${tradeBBS.group_id }"/>
	</c:if>
	<c:if test="${ ! empty tradeBBS.parent_id }">
		<form:hidden path="parent_id" value="${tradeBBS.parent_id }"/>
	</c:if>
											<!-- tradeBBS���� ������ -->
<table>
	<tr><th>����</th><td><form:input path="title" value="${title }"/></td></tr>	
	<tr><th>����</th><td><form:input path="price"/></td></tr>	
	<tr><th>��ȣ</th><td><form:password path="password"/></td></tr>	
	<tr><th>�̹���</th><td><input type="file" name="IMAGENAME"></td></tr>	
	<tr><th>�۳���</th>
	<td><form:textarea path="content" rows="8" cols="40"/></td></tr>	
	<tr><td colspan="2" align="center">
		<input type="submit" value="�ۿø���">
		<input type="reset" value="���">
	</td></tr>	
</table>
</form:form>
</div>
</body>
</html>