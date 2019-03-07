<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach items="${userList}" var="user" varStatus="i">
	<tr class="userTr" data-userid="${user.userId}">
		<td>${i.index+1}</td>
		<td>${user.userId}</td>
		<td>${user.userNm}</td>
		<td>${user.alias}</td>
		<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd" /></td>
	</tr>
</c:forEach>

=====seperator=====
<!-- 첫번째 페이지 -->
<c:choose>
	<c:when test="${page == '1'}">
		<li class="disabled">
			<a aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
		</li>
	</c:when>
	<c:otherwise>
		<li>
			<a href="javascript:getUserPagingListHtml(1);" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:otherwise>
</c:choose>

<!-- 페이지 -->
<c:choose>
	<c:when test="${startPage == lastPageStartPage}">
		<%-- 마지막페이지가 해당되는 페이지의 시작페이지이면 다음로직 실행 --%>
		<c:forEach begin="${lastPageStartPage}" end="${lastPage}" var="i">
			<c:set var="active" value="" />
			<c:if test="${i == page}">
				<c:set var="active" value="active" />
			</c:if>
			<li class="${active}">
				<a href="javascript:getUserPagingListHtml(${i});">${i}</a>
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
				<a href="javascript:getUserPagingListHtml(${i});">${i}</a>
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
			<a href="javascript:getUserPagingListHtml(${lastPage});" aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</c:otherwise>
</c:choose>