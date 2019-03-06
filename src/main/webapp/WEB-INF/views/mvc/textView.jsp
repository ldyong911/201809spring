<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>textReq</h2>
	<form action="${cp}/mvc/textReq">
		<input type="text" name="userId" value="brown"/>
			<form:errors path="userVo.userId"/>	<br>
		<input type="password" name="pass" value="1234"/>
			<font color="red">${passwordLengthMsg}</font>
			<form:errors path="userVo.pass"/> <br>
		<input type="submit" value="전송"/>
	</form>
	
	<h2>textReqJsr303</h2>
	<form action="${cp}/mvc/textReqJsr303">
		<input type="text" name="userId" value="brown"/>
			<form:errors path="userVo.userId"/>	<br>
		<input type="password" name="pass" value="1234"/>
			<form:errors path="userVo.pass"/> <br>
		<input type="submit" value="전송"/>
	</form>
	
	<h2>textReqValJsr303</h2>
	<form action="${cp}/mvc/textReqValJsr303">
		<input type="text" name="userId" value="brown"/>
			<form:errors path="userVo.userId"/>	<br>
		<input type="password" name="pass" value="1234"/>
			<form:errors path="userVo.pass"/> <br>
		<input type="submit" value="전송"/>
	</form>
	
	<h2><a href="${cp}/mvc/throwArith">Arithmetic 예외 강제 발생</a></h2>
	
	<h2><a href="${cp}/mvc/throwNoFileException">NoFileException 예외 강제 발생</a></h2>
</body>
</html>