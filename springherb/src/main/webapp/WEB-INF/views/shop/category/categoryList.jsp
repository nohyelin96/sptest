<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty list }">
	<dd><a href="#">카테고리가 없습니다.</a></dd>
</c:if>
<c:if test="${!empty list }">
	<c:forEach var="vo" items="${list }">
		<dd>
			<a href
="<c:url value='/shop/product/productByCategory.do?categoryNo=${vo.categoryNo}&categoryName=${vo.categoryName}'/>">		
				${vo.categoryName }
			</a>
		</dd>
	</c:forEach>
</c:if>

    