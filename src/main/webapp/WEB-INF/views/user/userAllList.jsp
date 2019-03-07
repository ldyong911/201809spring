<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/dashboard.css" rel="stylesheet">
</head>

<body>
	<%@ include file="/WEB-INF/views/module/header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/WEB-INF/views/module/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">전체 사용자 리스트</h1>
				<!-- userList 정보를 화면에 출력하는 로직 작성 -->
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>사용자 아이디</th>
								<th>사용자 이름</th>
								<th>별명</th>
								<th>등록일시</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user" varStatus="i">
								<tr class="userTr" data-userid="${user.userId}">
									<td>${i.index+1}</td>
									<td>${user.userId}</td>
									<td>${user.userNm}</td>
									<td>--</td>
									<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

	<script>
		//문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function() {
			console.log("document ready");

			//사용자 tr 태그 클릭시 이벤트 핸들러
			//$(".userTr").click(function(){
			//});

			$(".userTr").on("click", function() {
				console.log("userTr click");
				//클릭한 userTr태그의 userId 값을 출력
				//console.log($(this).children()[1].innerText);
				//console.log("data-userid : " + $(this).data("userid"));

				var userId = $(this).data("userid");

				//1.document
				//document.location = "/user?userId=" + userId;

				//2.form
				$("#userId").val(userId);
				//$("#frm").attr("action", "/user"); //속성바꿀때 사용
				$("#frm").submit();

			});

		});
	</script>

	<%
		//EL로 scope영역 객체에 접근하기위한 방법

		pageContext.getRequest().equals(request);
		pageContext.getSession().equals(session);

		request.getContextPath();
		((HttpServletRequest) pageContext.getRequest()).getContextPath();

		application.getContextPath();

		((HttpServletRequest) pageContext.getRequest()).getContextPath();
		pageContext.getServletContext().getContextPath();
	%>
	
	<!-- EL로 변환 -->
	<form id="frm" action="${cp}/user/user" method="get">
		<input type="hidden" id="userId" name="userId" />
	</form>
</body>
</html>