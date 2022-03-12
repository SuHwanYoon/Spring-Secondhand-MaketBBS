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
	if(f.addr.value == ''){alert("주소를 입력하세요"); return false;}
	if(f.id.value == ''){alert("계정을 입력하세요"); return false;}
	if(f.phone.value == ''){alert("연락처를 입력하세요"); return false;}
	if(f.pwd.value == ''){alert("비밀번호를 입력하세요"); return false;}
	if(f.pwd.value != f.CONFIRM.value){
		alert("암호가 일치하지 않습니다"); return false;
	}	
	
	if(f.job.selectedIndex < 1){
		alert("직업을 선택하세요"); return false;
	}
	if(!confirm("정말로 수정하시겠습니까?")) return false;
}
</script>
<h2 align="center">개인 정보 수정</h2>
<div align="center">
<form:form action="../userentry/userModifydo.html" method="post" 
modelAttribute="USERIMPO" name="frm" onSubmit="return check(this)">
<input type="hidden" name="idChecked">
이름: <form:input path="name" value="${USERIMPO.name }" readonly="true"/><br/>
계정: <form:input path="id" value="${USERIMPO.id }" readonly="true" /><br/>
주소: <form:input path="addr" value="${USERIMPO.addr }"/><br/>
연락처: <form:input path="phone" value="${USERIMPO.phone }"/><br/>
새로운암호: <form:password path="pwd" /><br/>
암호확인: <input type="password" name="CONFIRM"/><br/>
이메일: <form:input path="email" value="${USERIMPO.email }"/><br/>
직업: <form:select path="job">
	<form:option value="---선택하세요---"/>
	<form:option value="회사원"/>
	<form:option value="학생"/>
	<form:option value="기타"/>
</form:select><br/><br/>

<div align="center">
	<input type="submit" value="수정하기">
	<input type="reset" value="취소">
</div>
</form:form>
</div>
</body>
</html>