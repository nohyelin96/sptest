<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>zipcode/zipcode.jsp</title>
<link rel="stylesheet" type="text/css" 
	href="<c:url value='/resources/css/mainstyle.css'/>">
<style type="text/css">
	p, label{
		font-size: 0.9em;
	}
	.box2{
		width: 490px;
	}
	#divTable{
		width: 500px;
		margin: 15px 0;
	}
	h1{
		font-size: 1.5em;
		margin-bottom: 25px;
	}
	.error{
		color: red;
		display: none;
	}
	#page{
		margin: 10px 0;
		text-align: center;
	}
</style>
<script type="text/javascript" 
src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$("#dong").focus();
		
		$("form[name=frm]").submit(function(){
			if($("#dong").val().length<1){
				$(".error").show();
				$("#dong").focus();
				event.preventDefault();
			}	
		});
		
	});
	
	function setAddress(zipcode, address){
		$(opener.document).find('#zipcode').val(zipcode);
		$(opener.document).find('input[name=address]').val(address);
		
		self.close();
	}
	
	function pageFunc(curPage){
		$("#currentPage").val(curPage);
		
		$("form[name=frm]").submit();
	}
</script>
</head>
<body>
	<h1>우편번호 검색</h1>
	<p>찾고 싶으신 주소의 동(읍, 면)을 입력하세요</p>
	<form name="frm" method="post" 
		action="<c:url value='/zipcode/zipcode.do'/>">
		<input type="text" name="currentPage" id="currentPage" 
			value="1">
		
		<label for="dong">지역명</label>
		<input type="text" name="dong" id="dong" value="${param.dong}">
		<input type="submit" value="찾기">
		<span class="error">지역명을 입력하세요</span>
	</form>
	<c:if test="${list!=null }">	
		<div id="divTable">
			<table class="box2" 
			summary="우편번호 검색 결과에 관한 표로써  우편번호, 주소에 대한 정보를 제공합니다.">
				<colgroup>
					<col style="width:20%">
					<col style="width:*">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">우편번호</th>
						<th scope="col">주소</th>
					</tr>	
				</thead>
				<tbody>
					<c:if test="${empty list }">
						<tr style="text-align: center">
							<td colspan="2">해당하는 주소가 존재하지 않습니다.</td>
						</tr>
					</c:if>	
					<c:if test="${!empty list }">
						<!-- 반복시작 -->
						<c:forEach var="vo" items="${list }">
							<c:set var="address" 
								value="${vo.sido} ${vo.gugun } ${vo.dong }"/>
							<c:set var="bunji" value="${vo.startbunji }"/>
							<c:if test="${!empty vo.endbunji }">
								<c:set var="bunji" value="${bunji } ~ ${vo.endbunji }"/>
							</c:if>	
						
							<tr>
								<td>${vo.zipcode}</td>
								<td>
									<a href="#" 
				onclick="setAddress('${vo.zipcode}','${address}')">
									${address} ${bunji}
									</a>
								</td>
							</tr>							
						</c:forEach>
						<!-- 반복끝 -->
					</c:if>			
				</tbody>
			</table>
			<div id="page">
				<!-- 이전블럭으로 이동 -->
				<c:if test="${pagingInfo.firstPage>1 }">
					<a href="#" 
						onclick="pageFunc(${pagingInfo.firstPage-1})">
						<img src
						="<c:url value='/resources/images/first.JPG'/>" 
							alt="이전 블럭으로">
					</a>
				</c:if>
				
				<!-- 페이지 번호 추가 -->						
				<!-- [1][2][3][4][5][6][7][8][9][10] -->
				<c:forEach var="i" begin="${pagingInfo.firstPage }" 
					end="${pagingInfo.lastPage }">
					<c:if test="${i==param.currentPage }">
						<span style="color:blue;font-weight: bold">
							${i }</span>
					</c:if>
					<c:if test="${i!=param.currentPage }">
						<a href="#" onclick="pageFunc(${i})">
							[${i }]</a>
					</c:if>
				</c:forEach>	
				<!--  페이지 번호 끝 -->
				
				<!-- 다음블럭으로 이동 -->
				<c:if test="${pagingInfo.lastPage< pagingInfo.totalPage}">
					<a href="#"
						onclick="pageFunc(${pagingInfo.lastPage+1})">
						<img src="<c:url value='/resources/images/last.JPG'/>" 
							alt="다음 블럭으로">
					</a>
				</c:if>
			</div>
		</div>	
	</c:if>
</body>
</html>












