<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    

<style type="text/css">
	#divNotice{
		width: 310px;
	}
	#divNotice span{
		margin: 0 0 0 160px;
	}
	#divNotice .line img{
		width:310px;
		height: 6px;
	}
	.tblNotice{
		width: 300px;
	}
</style>

<div id="divNotice">
	<div>
		<img src="${pageContext.request.contextPath}/resources/images/notice2.JPG" 
			alt="공지사항 이미지">
		<span>
			<a href="<c:url value='/board/list.do'/>">
				<img src="${pageContext.request.contextPath}/resources/images/more.JPG" 
					alt="more이미지">
			</a>	
		</span>
	</div>
	<div class="line">
		<img src="${pageContext.request.contextPath}/resources/images/Line.JPG" 
			alt="line 이미지">
	</div>
	<div>
		<table class="tblNotice" summary="최근 공지사항 6건을 보여주는 표입니다.">
			<tbody>
				<c:if test="${empty list }">				
					<tr>
						<td>공지사항이 없습니다.</td>
					</tr>
				</c:if>	
				<c:if test="${!empty list }">				
					<!-- 반복 시작 -->	
					<c:forEach var="vo" items="${list }">	
						<tr>
							<td>
								<img src="${pageContext.request.contextPath}/resources/images/dot.JPG">
								<a href
					="<c:url value='/board/detail.do?no=${vo.no}'/>">
									<c:if test="${fn:length(vo.title)>30}">
										${fn:substring(vo.title, 0,30) }...
									</c:if>
									<c:if test="${fn:length(vo.title)<=30}">
										${vo.title}
									</c:if>	
								</a>
							</td>	
						</tr>
					<!-- 반복 끝 -->
					</c:forEach>
				</c:if>
			</tbody>			
		</table>
	</div>
</div>
