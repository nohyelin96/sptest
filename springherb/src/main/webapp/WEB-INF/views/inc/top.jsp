<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mainstyle.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/clear.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/layout.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mystyle.css"/>
<script type="text/javascript" 
	src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>

<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
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
			<h1><a href="<c:url value='/index.do'/>">
			<img alt="로고 이미지" src="${pageContext.request.contextPath}/resources/images/herbLogo7.jpg" height="95px" /></a></h1>
			<nav id="headerRight">
				<ul class="views">
					<!-- 로그인 안된 경우 -->
					<c:if test="${empty sessionScope.userid }">										
						<li><a href="<c:url value='/login/login.do'/>">로그인</a></li>
						<li><a href="<c:url value='/member/agreement.do'/>">회원가입</a></li>
					</c:if>
					
					<!-- 로그인 된 경우 -->
					<c:if test="${!empty sessionScope.userid }">										
						<li><a href="<c:url value='/login/logout.do'/>">로그아웃</a></li>
						<li><a href="<c:url value='/member/memberEdit.do'/>">회원정보수정</a></li>
						<li><a href="<c:url value='/member/memberOut.do'/>">회원탈퇴</a></li>
					</c:if>
										            
					<li><a href="<c:url value='/shop/cart/cartList.do'/>">장바구니</a></li>
					<li><a href="<c:url value='/shop/order/orderList.do'/>">주문내역</a></li>
					<li><a href="<c:url value='/shop/mypage.do'/>">마이페이지</a></li>
					<li><a href="<c:url value='/board/list.do'/>">고객센터</a></li>					
				</ul>				
			</nav>
		</header>

		<nav id="top_navi">
			<div id='cssmenu'>
				<ul>			   
				   <li><a href="<c:url value='/admin/manager/join.do'/>"><span>베스트</span></a></li>
				   <li><a href="<c:url value='/admin/manager/authList.do'/>"><span>자유게시판</span></a></li>
				   <li><a href="#"><span>이용후기</span></a></li>
				   <li><a href="#"><span>Q&A</span></a></li>
				   <li><a href="#"><span>FAQ</span></a></li>
				   <li class='last'><a href="#"><span>이벤트&쿠폰</span></a></li>
				</ul>
			</div>
		</nav>
		
		<!-- 라인 이미지 영역-->
		<div id="topLine" class="top_Line" 
		style="background:url(${pageContext.request.contextPath}/resources/images/line6.jpg)  repeat-x;font-size:7px">
			&nbsp;
		</div>
		
		<!-- container -->
		<div id="container">
			<nav>
				<dl id="leftNavi">
					<!-- category list -->
					<dt>허브</dt>
					<!-- <dd><a href="default.jsp">허브차</a></dd>
					<dd><a href="default.jsp">아로마 오일</a></dd>
					<dd><a href="default.jsp">향초</a></dd>
					<dd><a href="default.jsp">허브비누</a></dd> -->
					<c:import url="/shop/category/categoryList.do"></c:import>
				</dl>
			</nav>
			<section id="contents">