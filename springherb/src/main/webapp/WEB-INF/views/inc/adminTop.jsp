<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>herbmall 관리자</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mainstyle.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/clear.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/layout.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mystyle.css" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.4.1.js'/>"></script>
<!--[if lt IE 9]>
      <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>      
    <![endif]-->
 </head>

<body>	
	<!-- 상단 띠 이미지 영역-->
	<div id="topImg" class="top_Img" style="background:url(${pageContext.request.contextPath}/resources/images/bg_top1.PNG)  repeat-x">
		&nbsp;
	</div>

	<div id="wrap">
		<!-- header -->		
		<header id="header">
			<h1><a href="<c:url value='/admin/adminMain.do' />">
			<img alt="로고 이미지" src="${pageContext.request.contextPath}/resources/images/admin_logo2.jpg" 
			height="95px" /></a></h1>
			<nav id="headerRight">
				<ul class="views">	
					<c:if test="${!empty sessionScope.adminUserid }">
						<li><span style="color:gray;font-size:1em">관리자 (${sessionScope.adminUserName}) 님 </span></li>
						<li><a href="<c:url value='/admin/login/logout.do' />">로그아웃</a> | </li>
					</c:if>
					<li><a href="<c:url value='/index.do' />">내쇼핑몰 가기</a></li>
				</ul>
			</nav>
		</header>

		<!-- 라인 이미지 영역-->
		<div id="topLine" class="top_Line" style="background:url(${pageContext.request.contextPath}/resources/images/line6.jpg)  repeat-x;font-size:7px">
			&nbsp;
		</div>

		<!-- container -->
		<div id="container">
			<nav>
				<dl id="leftNavi">
				<!-- category list -->
				<dt>관리자 메뉴</dt>
			<!-- 좌측 메뉴 -->
 				<dd><a href="<c:url value='/admin/manager/join.do' />">관리자등록</a></dd>
 				<dd><a href="<c:url value='/admin/product/productWrite.do' />">상품등록</a></dd>
				<dd><a href="<c:url value='/admin/product/productList.do' />">상품목록</a></dd>
			</dl>
		</nav>
		<section id="contents">

