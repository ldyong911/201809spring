<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${cp}/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		console.log("ajaxView.jsp");
		
		//jsonData 요청
		$("#jsonReqBtn").on("click", function(){
			//jsonView();
			
			//responseBody();
			
			requestBody();
		});
	});
	
	function jsonView(){
		$.ajax({
			url : "${cp}/ajax/jsonView",
			method : "post",
			success : function(data){
				var arr = data.rangerList;
										
				var html = "";
				for(var i=0; i<arr.length; i++){
					html += "<tr>";
					html += "	<td>" + arr[i] + "</td>";
					html += "</tr>";
				}
				$("#jsonRecvTbody").html(html);
			}
			
		});
	}
	
	function responseBody(){
		$.ajax({
			url : "${cp}/ajax/responseBody",
			method : "post",
			dataType : "json",	//server에게 희망하는 리턴타입을 명시
			success : function(data){
				var arr = data;
										
				var html = "";
				for(var i=0; i<arr.length; i++){
					html += "<tr>";
					html += "	<td>" + arr[i] + "</td>";
					html += "</tr>";
				}
				$("#jsonRecvTbody").html(html);
			}
			
		});
	}
	
	function requestBody(){
		var data = {userId : "brown", userNm : "브라운"};
		$.ajax({
			url : "${cp}/ajax/requestBody",
			method : "post",
			//data : "userId=brown&userNm=브라운",
			//data : $("#frm").serialize(),	//보낼 파라미터가 많을때 사용하는 유용한 방법
			data : JSON.stringify(data), //JSON.stringify(데이터)는 해당 데이터를 문자열로 변환해줌
			dataType : "json",	//server로부터 받으려는 데이터타입
			contentType : "application/json; charsert=utf-8", //client가 전송하는 데이터타입 
			success : function(data){
				/*
				var arr = data;
										
				var html = "";
				for(var i=0; i<arr.length; i++){
					html += "<tr>";
					html += "	<td>" + arr[i] + "</td>";
					html += "</tr>";
				}
				
				$("#jsonRecvTbody").html(html);
				*/
				
				$("#jsonRecvTbody").html("<tr><td>" + data.userId + "</td></tr>");
			}
			
		});
	}
		
</script>
</head>
<body>
	<form id="frm">
		<input type="text" name="userId" value="brown"/>
		<input type="text" name="userNm" value="브라운"/>
	</form>
	
	<h2>ajaxView.jsp</h2>
	<h3>json 수신</h3>
	<div>
		<button id="jsonReqBtn">jsonData요청</button>
		<div id="jsonRecv"></div>
		<table>
			<thead>
				<tr>
					<th>이름</th>
				</tr>
			</thead>
			<tbody id="jsonRecvTbody">
			</tbody>
		</table>
	</div>
</body>
</html>