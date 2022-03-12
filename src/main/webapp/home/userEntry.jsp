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
function idCheck(){
	if(document.frm.id.value == ''){
		alert("������ �Է��ϼ���"); document.frm.id.focus();
		return;
	}
	var url = "../userentry/idCheck.html?USER_ID="+document.frm.id.value;
	window.open(url,"_blank_","width=450,height=200")
}
function check(f){
	if(f.name.value == ''){alert("�̸��� �Է��ϼ���"); return false;}
	if(f.idChecked.value == ''){alert("�ߺ� �˻縦 ���ּ���"); return false;}
	if(f.addr.value == ''){alert("�ּҸ� �Է��ϼ���"); return false;}
	if(f.id.value == ''){alert("������ �Է��ϼ���"); return false;}
	if(f.phone.value == ''){alert("����ó�� �Է��ϼ���"); return false;}
	if(f.pwd.value == ''){alert("��й�ȣ�� �Է��ϼ���"); return false;}
	if(f.pwd.value != f.CONFIRM.value){
		alert("��ȣ�� ��ġ���� �ʽ��ϴ�"); return false;
	}	
	if(!f.gender[0].checked && !f.gender[1].checked){
		alert("������ �����ϼ���"); return false;
	}
	if(f.job.selectedIndex < 1){
		alert("������ �����ϼ���"); return false;
	}
	if(!confirm("������ �����Ͻðڽ��ϱ�?")) return false;
}
</script>
<h2 align="center">���� ���� �Է�</h2>
<div align="center">
<form:form action="../userentry/entry.html" method="post" 
modelAttribute="user" name="frm" onSubmit="return check(this)">
<input type="hidden" name="idChecked">
�̸�: <form:input path="name"/><br/>
����: <form:input path="id" id="ID"/>
<input type="button" value="�ߺ��˻�" onClick="idCheck()"><br/>
�ּ�: <form:input path="addr"/><br/>
����ó: <form:input path="phone"/><br/>
��ȣ: <form:password path="pwd"/><br/>
��ȣȮ��: <input type="password" name="CONFIRM"/><br/>
����: ��<form:radiobutton path="gender" value="M"/>,
��<form:radiobutton path="gender" value="F"/><br/>
�̸���: <form:input path="email"/><br/>
����: <form:select path="job">
	<form:option value="---�����ϼ���---"/>
	<form:option value="ȸ���"/>
	<form:option value="�л�"/>
	<form:option value="��Ÿ"/>
</form:select><br/><br/>

<div align="center">
	<input type="submit" value="����">
	<input type="reset" value="���">
</div>
</form:form>
</div>
</body>
</html>