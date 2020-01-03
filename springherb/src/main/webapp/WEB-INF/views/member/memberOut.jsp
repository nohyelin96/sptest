<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>

<style type="text/css">
	.error{
		color: red;
		display: none;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#pwd").focus();
		
		$(".simpleForm form[name=frmOut]").submit(function(){			
			if($("#pwd").val().length<1){
				$("#pwd").next().show();
				$("#pwd").focus();
				event.preventDefault();
			}else{
				$("#pwd").next().hide();
				
				if(!confirm("회원탈퇴하시겠습니까?")){
					event.preventDefault();
				}
			}
		});
	});
</script>
<article class="simpleForm">
	<form name="frmOut" method="post" 
		action="<c:url value='/member/memberOut.do'/>">
		<fieldset>
			<legend>회원탈퇴</legend>
			<p class="p">회원탈퇴하시겠습니까?</p>
					
			<div>
				<label for="pwd" >비밀번호</label>
				<input type="password" name="pwd" id="pwd">
				<span class="error">비밀번호를 입력하세요</span>
			</div>
			<div class="align_center">
				<input type="submit" value="회원탈퇴">
				<input type="reset" value="취소">				
			</div>
		</fieldset>
	</form>
</article>
<%@ include file="../inc/bottom.jsp" %>