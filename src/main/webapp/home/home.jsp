<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
table{
	width:90%; height:80%; border:1px solid blue;
	border-collapse:collapse; margin-left:20px; margin-right:20px;
	background-color:skyblue;
}
td.main {width:30%; border:1px solid blue;}
#menu {margin-left:10px; margin-top:10px; width:90%;
		border:1px dashed red; background-color:orange;}
#content {background-color:wheat;}
#login {margin-left:10px; margin-top:10px; width:90%;
		border:1px dashed green; background-color:orange;}		
</style>
</head>
<body onload="startClock()">
<script type="text/javascript">
function workingClock(){
	var days=["��","��","ȭ","��","��","��","��"];
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() +1;
	if(month < 10) month = "0"+month;
	var date = today.getDate();
	if(date < 10) date = "0"+date;
	var index = today.getDay();
	var day = days[index];
	var hour = today.getHours();
	var min = today.getMinutes();
	if(min < 10)min = "0"+min;
	var sec = today.getSeconds();
	if(sec < 10 ) sec = "0"+sec;
	var str = year+"-"+month+"-"+date+" "+day+" "+hour+":"+min+":"+sec;
	document.getElementById("clock").innerHTML = str;
}
function startClock(){
	setInterval(workingClock, 1000);//1�ʸ��� �Լ� ȣ��
}

</script>
<header>
<div align="center">
<a href="../home/home.html"><img alt="" src="../imgs/homehome.png" height="50px" width="50px" ></a>
<font size="20px">�츮 ���� ����</font>
</div>

</header>
<section>
	<table id="body">
		<tr>
			<td width="40%" height="300px" class="main">
			<div id="login">
				<c:choose>
					<c:when test="${sessionScope.LOGINUSER != null }">
						<jsp:include page="logout.jsp"/>
					</c:when>
					<c:when test="${RELOGIN != null }"><!-- ������������ ��� jsp -->
						<jsp:include page="${RELOGIN }"/>
					</c:when>
					<c:otherwise>
						<jsp:include page="../login/login.html"/>	
					</c:otherwise>
				</c:choose><!-- �����޽��� �������ִ� Loginuser��ü�� �������� -->
				
			</div>
				<div id="menu">
					<a href="../home/home.html">Ȩ����</a><br/>
					<a href="../home/intro.html?BODY=intro.jsp">Ȩ������ �Ұ�</a><br/>
					<c:choose>
						<c:when test="${sessionScope.MAN != null }">
							<a href="home.jsp?BODY=noticeInput.jsp">�������� ����</a><br/>
							<a href="checkAdmin.do">�������� ����2</a><br/>
						</c:when>
					</c:choose>
					<a href="../userentry/userModifyForm.html">������</a><br/><br/>
					<a href="../write/writeForm.html">�����λ羲��</a><br/>
					<a href="../write/read.html">�����λ���</a><br/><br/>
						<c:choose>
						<c:when test="${sessionScope.MAN != null }">
							<a href="home.jsp?BODY=inputItem.jsp">��ǰ ���</a><br/>
						</c:when>
					</c:choose>
					<a href="../write/imageForm.html">����۾���</a><br/>
					<a href="../write/imageList.html">�츮���׼���</a><br/><br/>
					<a href="../write/tradeForm.html">�ŷ� �۾���</a><br/>
					<a href="../write/tradeList.html">�츮��������</a><br/>
					<a href="../read/cartConfirm.html">����</a><br/>
				</div>
			</td>
			<td id="content">
				<c:if test="${BODY == null }">
					<img alt="" src="../imgs/exchange.jpg" height="250px" width="90%" >
				</c:if>
				<c:if test="${BODY != null}"><!-- ���������� �ȵȴ� -->
					<jsp:include page="${BODY}"/>				
				</c:if><!-- �޴�����κ��� Ŭ���Ҷ� �����Բ� �ϱ�  �ȱ׷��� ������ �����°ɷ� �����ؼ� ������-->
			</td>
		</tr>
	</table>
</section>
<footer>
	<h3 align="center">CopyLeft 2022. YoonSuHwan Spring Project
	<font color="red"><span id="clock"></span></font></h3>
</footer>
</body>
</html>