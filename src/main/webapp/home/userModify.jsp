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
	if(f.addr.value == ''){alert("�ּҸ� �Է��ϼ���"); return false;}
	if(f.id.value == ''){alert("������ �Է��ϼ���"); return false;}
	if(f.phone.value == ''){alert("����ó�� �Է��ϼ���"); return false;}
	if(f.pwd.value == ''){alert("��й�ȣ�� �Է��ϼ���"); return false;}
	if(f.pwd.value != f.CONFIRM.value){
		alert("��ȣ�� ��ġ���� �ʽ��ϴ�"); return false;
	}	
	
	if(f.job.selectedIndex < 1){
		alert("������ �����ϼ���"); return false;
	}
	if(!confirm("������ �����Ͻðڽ��ϱ�?")) return false;
}
</script>
<h2 align="center">���� ���� ����</h2>
<div align="center">
<form:form action="../userentry/userModifydo.html" method="post" 
modelAttribute="USERIMPO" name="frm" onSubmit="return check(this)">
<input type="hidden" name="idChecked">
�̸�: <form:input path="name" value="${USERIMPO.name }" readonly="true"/><br/>
����: <form:input path="id" value="${USERIMPO.id }" readonly="true" /><br/>
�ּ�: <form:input path="addr" value="${USERIMPO.addr }"/><br/>
����ó: <form:input path="phone" value="${USERIMPO.phone }"/><br/>
���ο��ȣ: <form:password path="pwd" /><br/>
��ȣȮ��: <input type="password" name="CONFIRM"/><br/>
�̸���: <form:input path="email" value="${USERIMPO.email }"/><br/>
����: <form:select path="job">
	<form:option value="---�����ϼ���---"/>
	<form:option value="ȸ���"/>
	<form:option value="�л�"/>
	<form:option value="��Ÿ"/>
</form:select><br/><br/>

<div align="center">
	<input type="submit" value="�����ϱ�">
	<input type="reset" value="���">
</div>
</form:form>
</div>
</body>
</html>