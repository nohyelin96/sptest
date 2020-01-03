<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bigImage.jsp</title>
<style type="text/css">
	.divImg{
		text-align: center;
	}
</style>
<script type="text/javascript" 
	src="<c:url value='/resources/js/jquery-3.4.1.min.js' />"></script>
<script type="text/javascript">
	$(function(){
		$(".divImg button").click(function(){
			self.close();
		});
	});
</script>
</head>
<body>
<div class="divImg">
	<img src="<c:url value='/resources/pd_images/${param.imageURL}'/>" 
		alt="${param.productName }" width="390">
	<p>
		<button type="button">닫기</button>
	</p>
</div>

</body>
</html>