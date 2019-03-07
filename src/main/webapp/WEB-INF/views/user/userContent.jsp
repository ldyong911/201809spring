<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="page-header">사용자 정보 조회(tiles)</h1>
<form action="${cp}/user/userModifyForm" method="get"
  class="form-horizontal" role="form">
  
<input type="hidden" id="userId" name="userId" value="${userVo.userId}"/>

<div class="form-group">
	<label for="userId" class="col-sm-2 control-label">사진</label>
	<div class="col-sm-10">
		<img src="${cp}/user/profileImg?userId=${userVo.userId}"/>
	</div>
</div>

<div class="form-group">
	<label for="userId" class="col-sm-2 control-label">사용자 아이디</label>
	<div class="col-sm-10">
		<label class="control-label">${userVo.userId}</label>
	</div>
</div>

<div class="form-group">
	<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
	<div class="col-sm-10">
		<label class="control-label">${userVo.userNm}</label>
	</div>
</div>

<div class="form-group">
	<label for="alias" class="col-sm-2 control-label">별명</label>
	<div class="col-sm-10">
		<label class="control-label">${userVo.alias}</label>
	</div>
</div>

<div class="form-group">
	<label for="addr1" class="col-sm-2 control-label">주소</label>
	<div class="col-sm-10">
		<label class="control-label">${userVo.addr1}</label>
	</div>
</div>

<div class="form-group">
	<label for="addr2" class="col-sm-2 control-label">상세주소</label>
	<div class="col-sm-10">
		<label class="control-label">${userVo.addr2}</label>
	</div>
</div>

<div class="form-group">
	<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
	<div class="col-sm-7">
		<label class="control-label">${userVo.zipcode}</label>
	</div>
</div>

<div class="form-group">
	<label for="reg_dt" class="col-sm-2 control-label">등록일자</label>
	<div class="col-sm-10">
		<label class="control-label">
			<fmt:formatDate value="${userVo.reg_dt}" pattern="yyyy-MM-dd"/>
			</label>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">사용자 수정</button>
		</div>
	</div>
	
</form>

<script>
	<c:if test="${msg != null}">
		alert("${msg}");
	</c:if>
</script>