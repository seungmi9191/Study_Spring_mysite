<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>modifyform</title>
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
	
					<form id="modify-form" name="modifyForm" method="get" action="${pageContext.request.contextPath}/user/modify">
						<input type="hidden" name="no" value="${userVo.no}"/>
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="${userVo.name}" />
	
						<label class="block-label" for="email">이메일</label>
						<b>${userVo.email}</b>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="" />
						
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" ${userVo.gender eq 'female'?"checked":""}>
							<label>남</label> <input type="radio" name="gender" value="male" ${userVo.gender eq 'male'?"checked":""}>
							
						</fieldset>
						
						<input type="submit" value="수정완료">
						
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- footer -->
		
	</div> <!-- /container -->

</body>
</html>
