<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../../inc/top.jsp"%>

<style type="text/css">
	.pdLine{
		padding: 10px;
		border: 1px solid lightGray;
	}
	.divPd{
		float: left;
		width: 140px;
		padding: 20px;
		margin: 0 10px 10px 0;
		text-align: center;
		border: 1px solid lightGray;
	}
	.divAll{
		width: 800px;
	}
	
	.pdEvent{		
		clear:both;
		padding: 20px 0 10px 0;
	}
	.spEvent{
		margin: 0 0 0 10px;
		font-size: 1.5em;
		font-weight: bold;
	}
	
</style>

<p class="pdEvent">
	<img src="<c:url value='/resources/images/dotLong4.JPG'/>"
		align="absmiddle">
	<span class="spEvent">${param.categoryName }</span>
</p>
<div class="divAll">
	<c:if test="${empty list }">
		<div class="pdLine">
			해당하는 상품이 없습니다.
		</div>
	</c:if>
	<c:if test="${!empty list }">
		<c:forEach var="vo" items="${list }">
			<div class="divPd">
				<a href="<c:url value='/shop/product/productDetail.do?productNo=${vo.productNo}'/>">
					<img src
				="<c:url value='/resources/pd_images/${vo.imageURL}'/>" 
						alt="${vo.productName }">
				</a>
				<br>
				${vo.productName }<br>
				<fmt:formatNumber value="${vo.sellPrice }" 
					pattern="#,###"/>원
			</div>
		</c:forEach>
	</c:if>    
</div>

<%@ include file="../../inc/bottom.jsp"%>