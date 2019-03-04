<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		spring 파일업로드 설정
		1. commons-fileupload 의존 추가(pom.xml)
		2. multipartResolver 등록(servlet-context.xml)
		
		client side
		<form enctype="multipart/form-data">
	 -->
	 
	 <h2>spring part</h2>
	 <form action="/mvc/fileupload" method="post" enctype="multipart/form-data">
	 	<input type="text" name="userId" value="brown"/> <br>
	 	<input type="file" name="profile"/> <br>
	 	<input type="submit" value="전송"/>
	 </form>
</body>
</html>