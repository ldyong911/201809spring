<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="/css/signin.css" rel="stylesheet">

</head>
<body>
	<%-- 파라미터 전송을 위해 신경쓸 부분
  		 1.어디로 보내는지? : form action 속성
  		   -> 로그인 처리 요청 : LoginServlet doPost
  		 2.어떻게 보낼지? : form method 속성
  		   -> post : 사용자 비밀번호와 같이 보안 이슈가 발생할 수 있는 상황이므로 get 방식으로 보내지 않는다.
  		 3.뭘 보낼지? : input, select, textarea의 name 속성
  		   -> userId, password
  	 --%>

	<div class="container">
		<form class="form-signin" action="/login" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label>
			<input name="userId" type="text" id="userId" class="form-control" placeholder="Email address" required autofocus>
			<label for="inputPassword" class="sr-only">Password</label>
			<input name="pass" type="password" value="brown1234" id="inputPassword" class="form-control" placeholder="Password" required>
			<div class="checkbox">
				<label> 
					<input type="checkbox" id="rememberme" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="button" id="signin">
				Sign in
			</button>
		</form>
	</div>
	<!-- /container -->

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/cookieUtil.js"></script>
<script src="<%=request.getContextPath()%>/js/js.cookie.js"></script>
<!-- cookie 관련된 기능 처리에 필요 -->
<script>
	$(document).ready(function() {
		//userId 쿠키 값이 있을경우 userId input에 설정
		if (Cookies.get("userId")) {
			$("#userId").val(Cookies.get("userId"));
			$("#rememberme").prop("checked", true); //체크박스 속성은 prop 사용
		}

		//signin button 클릭 이벤트 핸들러
		$("#signin").click(function() {
			// 1.rememberme 체크박스가 체크 되었을 경우
			//   사용자 아이디 input에 저장된 값을
			//   cookie이름 : userId / cookieValue : 입력된 값으로 쿠키를 생성
			//   유효기간 30일로 설정하는 로직
			// 2.rememberme 체크박스가 체크 되어있지 않을경우
			//   cookie이름 : userId --> cookie 삭제

			//rememberme 체크박스 체크 된 경우
			if ($("#rememberme").prop("checked")) {
				Cookies.set("userId", $("#userId").val(), {
					expires : 30
				}); //이미 정의된 js.cookie.js 파일에서 set 메서드를 사용하는방법(name, value, 유효기간)
				Cookies.set("rememberme", "y", {
					expires : 30
				});
			} else {
				Cookies.remove("userId");
				Cookies.remove("rememberme");
			}

			$("form").submit(); //form이 하나이면 form 자체를 의미하는 코드(태그가 페이지에 한개일때 적용가능)

		});
	});
</script>
</html>