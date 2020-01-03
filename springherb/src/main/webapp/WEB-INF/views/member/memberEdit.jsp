<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>

<script type="text/javascript" 
	src="<c:url value='/resources/js/member.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$("#pwd").focus();
		
		$("#wr_submit").click(function(){
			if($("#pwd").val().length<1){
				alert("비밀번호를 입력하세요");
				$("#pwd").focus();
				event.preventDefault();				
			}else if($("#pwd").val()!=$("#pwd2").val()){
				alert("비밀번호가 일치하지 않습니다!");
				$("#pwd2").focus();
				event.preventDefault();				
			}else if(!validate_phone($("#hp2").val()) 
					|| !validate_phone($("#hp3").val())){
				alert("휴대폰번호는 숫자만 가능!");
				$("#hp2").focus();
				event.preventDefault();		
			}
			
		});
			
		
	});
		

</script>

<style type="text/css">
	.width_80{
		width:80px;
	}
	.width_350{
		width:350px;
	}	
</style>
<article>
<div class="divForm">
<form name="frm1" method="post" 
	action="<c:url value='/member/memberEdit.do'/>">
<fieldset>
	<legend>회원 정보 수정</legend>
    <div>        
        <label for="name">성명</label>
        <span>${vo.name}</span>
    </div>
    <div>
        <label for="userid">회원ID</label>
        <span>${sessionScope.userid}</span>
    </div>
    <div>
        <label for="pwd">비밀번호</label>
        <input type="Password" name="pwd" id="pwd">
    </div>
    <div>
        <label for="pwd2">비밀번호 확인</label>
        <input type="Password" name="pwd2" id="pwd2">
    </div>
    <div>
        <label for="zipcode">주소</label>
        <input type="text" name="zipcode" id="zipcode" ReadOnly  
        	title="우편번호" class="width_80"
        	value="${vo.zipcode}">
        <input type="Button" value="우편번호 찾기" id="btnZipcode" 
        	title="새창열림"><br />
        <span class="sp1">&nbsp;</span>
        <input type="text" name="address" ReadOnly title="주소"  
        	class="width_350"
        	value="${vo.address}"><br />
        <span class="sp1">&nbsp;</span>
        <input type="text" name="addressDetail" title="상세주소"  
        	class="width_350"
        	value="${vo.addressDetail}" >
    </div>
    <div>
        <label for="hp1">핸드폰</label>&nbsp;
        <select name="hp1" id="hp1" title="휴대폰 앞자리">
            <option value="010"
            	<c:if test="${vo.hp1=='010'}">
            		selected
            	</c:if>
            >010</option>
            <option value="011"
            	<c:if test="${vo.hp1=='011'}">
            		selected
            	</c:if>
            >011</option>
            <option value="016"
            	<c:if test="${vo.hp1=='016'}">
            		selected
            	</c:if>
            >016</option>
            <option value="017"
            	<c:if test="${vo.hp1=='017'}">
            		selected
            	</c:if>
            >017</option>
            <option value="018"
            	<c:if test="${vo.hp1=='018'}">
            		selected
            	</c:if>
            >018</option>
            <option value="019"
            	<c:if test="${vo.hp1=='019'}">
            		selected
            	</c:if>
            >019</option>
       	</select>
        -
        <input type="text" name="hp2" id="hp2" maxlength="4" title="휴대폰 가운데자리"
        	class="width_80" value="${vo.hp2}">-
        <input type="text" name="hp3" id="hp3" maxlength="4" title="휴대폰 뒷자리"
        	class="width_80" value="${vo.hp3}">
    </div>
    <div>
    	<!-- 직접 입력 처리 -->
    	<c:set var="etcYn" value="" />
    	<c:choose>
    		<c:when test="${vo.email2=='naver.com' || 
    			vo.email2=='hanmail.net' || vo.email2=='nate.com'
    			|| vo.email2=='gmail.com' || empty vo.email2}">
		    	<c:set var="etcYn" value="N" />    			
    		</c:when>
    		<c:otherwise>
		    	<c:set var="etcYn" value="Y" />    			
    		</c:otherwise>
    	</c:choose>
    	
        <label for="email1">이메일 주소</label>
        <input type="text" name="email1"  id="email1" 
        title="이메일주소 앞자리" value="${vo.email1}">@
        <select name="email2" id="email2"  title="이메일주소 뒷자리">        	        
            <option value="naver.com"
            	<c:if test="${vo.email2=='naver.com' }">            	
            		selected="selected"
            	</c:if>
            >naver.com</option>
            <option value="hanmail.net"
            	<c:if test="${vo.email2=='hanmail.net' }">            	
            		selected="selected"
            	</c:if>
            >hanmail.net</option>
            <option value="nate.com"
            	<c:if test="${vo.email2=='nate.com' }">            	
            		selected="selected"
            	</c:if>
            >nate.com</option>
            <option value="gmail.com"
            	<c:if test="${vo.email2=='gmail.com' }">            	
            		selected="selected"
            	</c:if>
            >gmail.com</option>
            <option value="etc"
            	<c:if test="${etcYn=='Y'}">            	
            		selected="selected"
            	</c:if>            	         	
            >직접입력</option>
        </select>
        <input type="text" name="email3" id="email3" title="직접입력인 경우 이메일주소 뒷자리"
        	<c:if test="${etcYn=='Y'}">    
        		style="visibility:visible;"
        		value="${vo.email2}"
        	</c:if>
        	<c:if test="${etcYn!='Y'}">    
        		style="visibility:hidden"
        	</c:if>
        	>
    </div>
    <div class="center">
         <input type="submit" id="wr_submit" value="수정">
    </div>
</fieldset>

</form>
</div>
</article>

<%@ include file="../inc/bottom.jsp"%>











