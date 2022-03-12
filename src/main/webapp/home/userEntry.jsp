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
		alert("계정을 입력하세요"); document.frm.id.focus();
		return;
	}
	var url = "../userentry/idCheck.html?USER_ID="+document.frm.id.value;
	window.open(url,"_blank_","width=450,height=200")
}
function check(f){
	if(f.name.value == ''){alert("이름을 입력하세요"); return false;}
	if(f.idChecked.value == ''){alert("중복 검사를 해주세요"); return false;}
	if(f.addr.value == ''){alert("주소를 입력하세요"); return false;}
	if(f.id.value == ''){alert("계정을 입력하세요"); return false;}
	if(f.phone.value == ''){alert("연락처를 입력하세요"); return false;}
	if(f.pwd.value == ''){alert("비밀번호를 입력하세요"); return false;}
	if(f.pwd.value != f.CONFIRM.value){
		alert("암호가 일치하지 않습니다"); return false;
	}	
	if(!f.gender[0].checked && !f.gender[1].checked){
		alert("성별을 선택하세요"); return false;
	}
	if(f.job.selectedIndex < 1){
		alert("직업을 선택하세요"); return false;
	}
	if(!confirm("정말로 가입하시겠습니까?")) return false;
}
</script>
<h2 align="center">개인 정보 입력</h2>
<div align="center">
<form:form action="../userentry/entry.html" method="post" 
modelAttribute="user" name="frm" onSubmit="return check(this)">
<input type="hidden" name="idChecked">
이름: <form:input path="name"/><br/>
계정: <form:input path="id" id="ID"/>
<input type="button" value="중복검사" onClick="idCheck()"><br/>
주소: <form:input path="addr"/><br/>
연락처: <form:input path="phone"/><br/>
암호: <form:password path="pwd"/><br/>
암호확인: <input type="password" name="CONFIRM"/><br/>
성별: 남<form:radiobutton path="gender" value="M"/>,
여<form:radiobutton path="gender" value="F"/><br/>
이메일: <form:input path="email"/><br/>
직업: <form:select path="job">
	<form:option value="---선택하세요---"/>
	<form:option value="회사원"/>
	<form:option value="학생"/>
	<form:option value="기타"/>
</form:select><br/><br/>

<div align="center">
	<input type="submit" value="가입">
	<input type="reset" value="취소">
</div>
</form:form>
</div>
</body>
</html>