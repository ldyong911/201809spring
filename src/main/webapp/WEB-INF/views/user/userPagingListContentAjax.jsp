<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1 class="page-header">전체 사용자 리스트(ajaxTiles)</h1>
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
		<tbody id="userListTbody">
		</tbody>
	</table>

	<form action="${cp}/user/userForm" method="get">
		<button type="submit" class="btn btn-default">사용자 등록</button>
	</form>

	<nav style="text-align: center;">
		<ul id="pagination" class="pagination">
		</ul>
	</nav>
</div>
<script>
	//사용자 배열을 이용하여 사용자 리스트 HTML을 생성
	function makeUserList(userList){
		var html = "";
		
		for(var i=0; i<userList.length; i++){
			var user = userList[i];
			html += "<tr class='userTr' data-userid='" + user.userId + "'>";
			html += "	<td>" + (i+1) + "</td>";
			html += "	<td>" + user.userId + "</td>";
			html += "	<td>" + user.userNm + "</td>";
			html += "	<td>" + user.alias + "</td>";
			html += "	<td>" + user.reg_dt_fmt + "</td>";
			html += "</tr>";
		}	
		$("#userListTbody").html(html);
	}
	
	//페이지네이션 HTML 생성
	function makePagination(page, pageSize, lastPageStartPage, lastPage, startPage, endPage){
		var html = "";
		
		//첫번째 페이지
		if(page == 1){
			html += "<li class='disabled'>";
			html += "	<a aria-label='Previous'>";
			html += "		<span aria-hidden='true'>&laquo;</span>";
			html += "	</a>";
			html += "</li>";
		}else{
			html += "<li>";
			html += "	<a href='javascript:getUserPagingList(1);' aria-label='Previous'>";
			html += "		<span aria-hidden='true'>&laquo;</span>";
			html += "	</a>";
			html += "</li>";
		}
		
		//페이지
		if(startPage == lastPageStartPage){
			for(var i=lastPageStartPage; i<=lastPage; i++){
				var active = "";
				if(i == page){
					active = "active";
				}
				html += "<li class='"+ active +"'>";
				html += "	<a href='javascript:getUserPagingList("+ i +");'>"+ i +"</a>";
				html += "</li>";
			}
		}else{
			for(var i=startPage; i<=endPage; i++){
				var active = "";
				if(i == page){
					active = "active";
				}
				html += "<li class='"+ active +"'>";
				html += "	<a href='javascript:getUserPagingList("+ i +");'>"+ i +"</a>";
				html += "</li>";
			}
		}
		
		//마지막 페이지
		if(page == lastPage){
			html += "<li class='disabled'>";
			html += "	<a aria-label='Next'>";
			html += "		<span aria-hidden='true'>&raquo;</span>";
			html += "	</a>";
			html += "</li>";
		}else{
			html += "<li>";
			html += "	<a href='javascript:getUserPagingList("+ lastPage +");' aria-label='Next'>";
			html += "		<span aria-hidden='true'>&raquo;</span>";
			html += "	</a>";
			html += "</li>";
		}
		
		$("#pagination").html(html);
	}
	
	//페이징리스트 json타입으로 받아서 ajax로 처리
	function getUserPagingList(page){
		$.ajax({
			url : "${cp}/user/userPagingListAjax",
			data : "page=" + page,
			success : function(data){
				console.log(data);
				
				makeUserList(data.userList);
				makePagination(data.page, data.pageSize, data.lastPageStartPage, data.lastPage, data.startPage, data.endPage);
			}
		});
	}
	
	//json타입을 html형식으로 받아서 ajax로 처리
	function getUserPagingListHtml(page){
		$.ajax({
			url : "${cp}/user/userPagingListAjaxHtml",
			data : "page=" + page,
			success : function(data){
				console.log(data);
				
				var htmlArr = data.split("=====seperator====="); //구분자로 잘라서 배열에 넣음
				$("#userListTbody").html(htmlArr[0]);
				$("#pagination").html(htmlArr[1]);
			}
		});
	}
	
	//문서로딩이 완료된 이후 이벤트 등록
	$(document).ready(function(){
		console.log("document ready");
		
		//페이징리스트를 json타입으로 받아 ajax 형식으로 변경
		//getUserPagingList(1);
		
		//json타입을 html형식으로 받아서 ajax로 처리
		getUserPagingListHtml(1);
		
		//msg 속서이 존재하면 alert, 존재하지 않으면 넘어가기
		<c:if test="${msg != null}">
			alert("${msg}");
			<%session.removeAttribute("msg");%> //session영역 속성이 존재하기때문에 알람창을 한번 띄운후 삭제
		</c:if>
		
		$(".userTr").on("click", function(){
			console.log("userTr click");
			//클릭한 userTr태그의 userId 값을 출력
			//console.log($(this).children()[1].innerText);
			//console.log("data-userid : " + $(this).data("userid"));
			
			var userId = $(this).data("userid");
			$("#userId").val(userId);
			
			//$("#frm").attr("action", "/user"); //속성바꿀때 사용
			$("#frm").submit();
			
		});
		
	});
</script>

<form id="frm" action="${cp}/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>