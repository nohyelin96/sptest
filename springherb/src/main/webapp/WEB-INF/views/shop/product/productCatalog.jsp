<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
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

<h2>상품 목록</h2>

<p class="pdEvent">
	<img src="${pageContext.request.contextPath }/resources/images/dotLong4.JPG"
		align="absmiddle">
	<span class="spEvent">신상품</span>
</p>

<!-- 신상품 include -->
<c:import url="/shop/product/productByEvent.do">
	<c:param name="eventName" value="NEW"></c:param>
</c:import>

<p class="pdEvent">
	<img src="${pageContext.request.contextPath }/resources/images/dotLong3.JPG"
		align="absmiddle">
	<span class="spEvent">인기상품</span>
</p>

<!-- 인기상품 include -->
<c:import url="/shop/product/productByEvent.do">
	<c:param name="eventName" value="BEST"></c:param>
</c:import>

<p class="pdEvent">
	<img src="${pageContext.request.contextPath }/resources/images/dotLong4.JPG"
		align="absmiddle">
	<span class="spEvent">MD 추천상품</span>
</p>

<!-- MD 추천상품 include -->
<c:import url="/shop/product/productByEvent.do">
	<c:param name="eventName" value="MD"></c:param>
</c:import>

