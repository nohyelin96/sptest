<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	//[1] write.jsp에서 글목록 클릭하면 get방식으로 이동
	//[2] write_ok.jsp에서 글쓰기 성공하면 get방식으로 이동	
	//[3] list.jsp에서 검색 버튼 클릭하면 post방식으로 submit
	//[4] list.jsp에서 페이지번호 클릭하면 get방식으로 이동
	
	//=> 글 전체 조회해서 출력
	//=> 검색어를 이용해서 검색
	//=> 페이지번호에 해당하는 글 조회
	
	//뷰페이지
	//request에 저장된 결과 읽어오기
	//List<BoardVO> list=(List<BoardVO>)request.getAttribute("list");
	//PagingVO pageVo=(PagingVO)request.getAttribute("pageVo");
	
	//String keyword=request.getParameter("searchKeyword");
	//String condition=request.getParameter("searchCondition");
	
	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>자유게시판 글 목록 - 허브몰</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" 
	href="<c:url value='/resources/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mystyle.css'/>" />
<script type="text/javascript" 
	src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>

<script type="text/javascript">	
	$(function(){
		$(".box2 tbody tr").hover(function(){
			$(this).css("background","lightblue");
		}, function(){
			$(this).css("background","");
		});	
	});
	
	function pageFunc(curPage){
		document.frmPage.currentPage.value=curPage;
		
		document.frmPage.submit();
	}
	
</script>
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }	
</style>	
</head>	
<body>
<h2>자유게시판</h2>
<c:if test="${!empty param.searchKeyword }">
	<p>검색어 : ${param.searchKeyword}, 
			${pageVo.totalRecord}건 검색되었습니다.</p>	
</c:if>

<!-- 페이징 처리 관련 form -->
<form action="<c:url value='/board/list.do'/>" 
	name="frmPage" method="post">
	<input type="text" name="searchCondition" 
		value="${param.searchCondition}">
	<input type="text" name="searchKeyword" 
		value="${param.searchKeyword}">
	<input type="text" name="currentPage" >
</form>

<div class="divList">
<table class="box2"
	 	summary="기본 게시판에 관한 표로써, 번호, 제목, 작성자, 작성일, 조회수에 대한 정보를 제공합니다.">
	<caption>기본 게시판</caption>
	<colgroup>
		<col style="width:10%;" />
		<col style="width:50%;" />
		<col style="width:15%;" />
		<col style="width:15%;" />
		<col style="width:10%;" />		
	</colgroup>
	<thead>
	  <tr>
	    <th scope="col">번호</th>
	    <th scope="col">제목</th>
	    <th scope="col">작성자</th>
	    <th scope="col">작성일</th>
	    <th scope="col">조회수</th>
	  </tr>
	</thead> 
	<tbody>
		<c:if test="${empty list }">
			<tr class="align_center">
				<td colspan="5">해당 글이 존재하지 않습니다.</td>
			</tr>
		</c:if>  
		<c:if test="${!empty list }">
			<c:set var="num" value="${pageVo.num }" />
			<c:set var="curPos" value="${pageVo.curPos }" />
			
			<c:forEach var="i" begin="1" end="${pageVo.pageSize }">
				<c:if test="${num>=1 }">
					<c:set var="vo" value="${list[curPos] }" />
					<c:set var="curPos" value="${curPos+1 }" />
					<c:set var="num" value="${num-1 }" />
	  	
					<tr  style="text-align:center">
						<td>${vo.no}</td>
						<td style="text-align:left">
							<a href
			="<c:url value='/board/countUpdate.do?no=${vo.no}'/>">
								${vo.title}
							</a></td>
						<td>${vo.name}</td>
						<td><fmt:formatDate value="${vo.regdate }" 
							pattern="yyyy-MM-dd"/>
						</td>
						<td>${vo.readcount}</td>		
					</tr> 
				  <!--반복처리 끝  -->
	  			</c:if>
	  		</c:forEach>
	  	</c:if>
	  </tbody>
</table>	   
</div>
<div class="divPage">
	<!-- 이전블럭으로 이동 -->
	<c:if test="${pageVo.firstPage>1 }">	
		<a href="#" onclick="pageFunc(${pageVo.firstPage-1})">
			<img src="<c:url value='/images/first.JPG'/>" alt="이전 블럭으로">
		</a>
	</c:if>
	<!-- 페이지 번호 추가 -->						
	<!-- [1][2][3][4][5][6][7][8][9][10] -->
	<c:forEach var="i" begin="${pageVo.firstPage }" 
		end="${pageVo.lastPage }">
		<c:if test="${i<=pageVo.totalPage }">
			<c:if test="${i==pageVo.currentPage }">
				<span style="color:blue;font-weight: bold">${i}</span>
			</c:if>
			<c:if test="${i!=pageVo.currentPage }">
				<a href="#" onclick="pageFunc(${i})">
					[${i}]</a>
			</c:if>
		</c:if>	
	</c:forEach>
	<!--  페이지 번호 끝 -->
	
	<!-- 다음블럭으로 이동 -->
	<c:if test="${pageVo.lastPage<pageVo.totalPage }">
		<a href="#" onclick="pageFunc(${pageVo.lastPage+1})">	
			<img src="<c:url value='/images/last.JPG'/>" alt="다음 블럭으로">
		</a>
	</c:if>	
</div>
<div class="divSearch">
   	<form name="frmSearch" method="post" 
   		action='<c:url value="/board/list.do"/>'>
        <select name="searchCondition">
            <option value="title" 
            	<c:if test="${param.searchCondition=='title' }">
            		selected="selected"
            	</c:if>
            >제목</option>
            <option value="content" 
            	<c:if test="${param.searchCondition=='content' }">
            		selected="selected"
            	</c:if>
            >내용</option>
            <option value="name" 
            	<c:if test="${param.searchCondition=='name' }">
            		selected="selected"
            	</c:if>
            >작성자</option>
        </select>   
        <input type="text" name="searchKeyword" title="검색어 입력"
        	value="${param.searchKeyword}">   
		<input type="submit" value="검색">
    </form>
</div>

<div class="divBtn">
    <a href='<c:url value="/board/write.do"/>' >글쓰기</a>
</div>

</body>
</html>

