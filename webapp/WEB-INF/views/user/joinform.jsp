<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<title>Mysite</title>
</head>
<body>

	<div id="container">

		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- /header -->

		<!-- navigation -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<!-- /navigation -->

		<div id="wrapper">
			<div id="content">
				<div id="user">

					<form id="join-form" name="joinForm" method="post"
						action="${pageContext.request.contextPath}/user/join">

						<label class="block-label" for="name">이름</label> 
						<input id="name" name="name" type="text" value=""> 
						
						<label class="block-label" for="email">이메일</label> 
						<input id="email" name="email" type="text" value=""> 
						
						<input id="btnEmailCheck" type="button" value="id 중복체크"> 
						<div id="msg"></div>
						
						<label class="block-label">패스워드</label> 
						<input name="password" type="password" value="">

						<fieldset>
							<legend>성별</legend>
							<label>여</label> 
							
							<input type="radio" name="gender" value="female" checked="checked"> <label>남</label> 
							<input type="radio" name="gender" value="male">
						</fieldset>

						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>

						<input type="submit" value="가입하기">

					</form>

				</div>
				<!-- /user -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- footer -->

	</div>
	<!-- /container -->

	<script type="text/javascript">
		//email 입력의 value를 찾아라.
		$("#btnEmailCheck").on("click", function(){
			var email = $("#email").val();
			console.log(email);

			$.ajax({
			      
				//url : "${pageContext.request.contextPath }/user/emailcheck?email="+email, //내가 보내줄 컨트롤러 지정   
			    url : "${pageContext.request.contextPath }/user/emailcheck", 
			    type : "post",
			    data : {email:email}, //?파라미터값=이름 → 여러개면 & / key : value
	
			    dataType : "json",
			    success : function(isExists){
			    console.log(isExists);
			    	
		         		/*성공시 처리해야될 코드 작성*/
				    	if(isExists==true) { 
				    		$("#msg").html("사용중인 아이디입니다.");
				    	}else {
				    		$("#msg").html("사용가능한 아이디입니다.");
				    	}
				    	
			    },
			    error : function(XHR, status, error) {
			        console.error(status + " : " + error);
			    }
		    });	//end ajax
		}); //end on
</script>
</body>
</html>