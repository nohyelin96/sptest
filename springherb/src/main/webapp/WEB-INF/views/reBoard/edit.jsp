<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html lang="ko">
<head>
<title>자료실 글 수정 - 허브몰</title>
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
		$(".divForm input[type=submit]").click(function(){
			$(".infobox").each(function(idx, item){
				if($(this).val().length<1){
					alert($(this).prev().text() + "을 입력하세요");
					$(this).focus();
					event.preventDefault(); //이벤트 진행을 막는다
					
					return false;  //each 탈출
				}
			});
		});
	});
</script>

</head>
<body>
<div class="divForm">
<form name="frmEdit" method="post" enctype="multipart/form-data"
	action="<c:url value='/reBoard/edit.do'/>"> 
    <!-- 수정처리시 no가 필요하므로 hidden 필드에 넣는다-->
    <input type="hidden" name="no" value="${reBoardVo.no}">
    <input type="text" name="oldFileName" value="${reBoardVo.fileName}">
    
    <fieldset>
	<legend>글수정</legend>
        <div class="firstDiv">
            <label for="title">제목</label>
            <input type="text" id="title" name="title"  
            	value="${reBoardVo.title}" class="infobox" />
        </div>
        <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" 
            	value="${reBoardVo.name}" class="infobox"/>
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" class="infobox"/>
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" 
            	value="${reBoardVo.email}"/>
        </div>
        <div>
            <label for="upfile">첨부파일</label>
            <input type="file" id="upfile" name="upfile"/>
            <br>
            <span class="sp1"></span>
            <c:if test="${!empty reBoardVo.fileName }">
            	<span style="color: green;font-weight: bold">
            		첨부파일을 새로 지정할 경우 기존 파일
            		<img src="<c:url value='/resources/images/file.gif'/>" 
            			alt="file이미지">
            		${reBoardVo.originalFileName }는 삭제됩니다.
            	</span>
            </c:if>
        </div>        
        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="12" cols="40">${reBoardVo.content}</textarea>
        </div>
        <div class="center">
            <input type = "submit" value="수정"/>
            <input type = "Button" value="글목록" 
onclick="location.href='<c:url value='/reBoard/list.do'/>'" />         
        </div>
	</fieldset>
</form>    
</div>

</body>
</html>