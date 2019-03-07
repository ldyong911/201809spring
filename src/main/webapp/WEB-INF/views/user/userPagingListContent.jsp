<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1 class="page-header">전체 사용자 리스트(tiles)</h1>
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

	<form action="${cp}/user/userForm" method="get">
		<button type="submit" class="btn btn-default">사용자 등록</button>
	</form>

	<nav style="text-align: center;">
		<ul class="pagination">
			<!-- 첫번째 페이지 -->
			<c:choose>
				<c:when test="${page == '1'}">
					<li class="disabled">
						<a aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="${cp}/user/userPagingList?page=1"
						aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:otherwise>
			</c:choose>

			<!-- 페이지 -->
			<c:choose>
				<c:when test="${startPage == lastPageStartPage}"> <%-- 마지막페이지가 해당되는 페이지의 시작페이지이면 다음로직 실행 --%>
					<c:forEach begin="${lastPageStartPage}" end="${lastPage}" var="i">
						<c:set var="active" value="" />
						<c:if test="${i == page}">
							<c:set var="active" value="active" />
						</c:if>
						<li class="${active}">
							<a href="${cp}/user/userPagingList?page=${i}">${i}</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${startPage}" end="${endPage}" var="i">
						<c:set var="active" value="" />
						<c:if test="${i == page}">
							<c:set var="active" value="active" />
						</c:if>
						<li class="${active}">
							<a href="${cp}/user/userPagingList?page=${i}">${i}</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>

			<!-- 마지막페이지 -->
			<c:choose>
				<c:when test="${page == (lastPage)}">
					<li class="disabled">
						<a aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="${cp}/user/userPagingList?page=${lastPage}"
						aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</div>

<script>
	//문서로딩이 완료된 이후 이벤트 등록
	$(document).ready(function(){
		console.log("document ready");
		
		//msg 속서이 존재하면 alert, 존재하지 않으면 넘어가기
		<c:if test="${msg != null}">
			alert("${msg}");
			<%session.removeAttribute("msg");%> //session영역 속성이 존재하기때문에 알람창을 한번 띄운후 삭제
		</c:if>
		
		//사용자 tr 태그 클릭시 이벤트 핸들러
		//$(".userTr").click(function(){
		//});
		
		$(".userTr").on("click", function(){
			console.log("userTr click");
			//클릭한 userTr태그의 userId 값을 출력
			//console.log($(this).children()[1].innerText);
			//console.log("data-userid : " + $(this).data("userid"));
			
			var userId = $(this).data("userid");
			
			// 1.document
			//document.location = "/user?userId=" + userId;
			
			// 2.form
			$("#userId").val(userId);
			//$("#frm").attr("action", "/user"); //속성바꿀때 사용
			$("#frm").submit();
			
		});
		
	});
</script>

<form id="frm" action="${cp}/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>