<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<style type="text/css">
	#agree{
		width:800px;
	}
	.divChk{
		text-align: right;
		margin:10px 0; 
	}
	.divChk ~ div{
		text-align: center;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#agree form[name=frmAgree]").submit(function(){
			if(!$("#chkAgree").is(":checked")){
				alert("약관에 동의하셔야합니다.");
				$("#chkAgree").focus();
				event.preventDefault();
			}	
		});
		
	});
	
</script>

<article id="agree">
	<h2>회원 약관</h2>
	<iframe src="<c:url value='/agree/provision.html'/>" 
		width="800" height="400"></iframe>
	<form name="frmAgree" method="post" 
		action="<c:url value='/member/register.do'/>">
		<div class="divChk">
			<input type="checkbox" name="chkAgree" id="chkAgree">
			<label for="chkAgree">약관에 동의합니다.</label>
		</div>
		<div>
			<input type="submit" value="확인">
			<input type="reset" value="취소">
		</div>		
	</form>
</article>
<%@ include file="../inc/bottom.jsp" %>



