<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>rangerList.jsp</h2>
	
	<table>
		<thead>
			<tr>
				<td>이름</td>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${rangers}" var="ranger">
				<tr>
					<td>${ranger}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	boardGb : ${boardGb} <br>
	boardGb2 : ${boardGb2} <br>
	sessionScope : ${sessionScope.boardGb2} <br>
</body>
</html>