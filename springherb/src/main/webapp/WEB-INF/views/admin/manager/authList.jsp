<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>authList.jsp</title>
</head>
<%@ include file="../../inc/top.jsp" %>
<script type="text/javascript" 
	src="<c:url value='/resources/js/member.js'/>"></script>

<body>
	<legend style="font-size: 1.3em; font-weight: bold;">[ 관리자 목록 ]</legend><br><br>
	<table border="1" style="width: 700px;">
		<tr>
			<th>관리자코드</th>
			<th>직급</th>
			<th>업무</th>
			<th>관리자레벨</th>
			<th>등록일</th>
		</tr>
		<!-- s반복문 시작 -->
		<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.authcode }</td>
			<td>${vo.authname }</td>
			<td>${vo.authdesc }</td>
			<td>${vo.authlevel }</td>
			<td>${vo.regdate }</td>
		</tr>
		</c:forEach>
		<!-- 반복문 끝 -->
	</table>
</body>
</html>
<%@ include file="../../inc/bottom.jsp"%>