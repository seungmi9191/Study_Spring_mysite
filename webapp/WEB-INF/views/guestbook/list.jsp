<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>방명록</title>
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
				<div id="guestbook">
					
					<form action="${pageContext.request.contextPath}/guest/insert" method="get">
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" value=" 확인 " /></td>
							</tr>
						</table>
					</form>
				
					<br/>
					<c:forEach items="${list}" var="guestVo">
						<table width=510 border=1>
							<tr>
								<td>${guestVo.no}</td>
								<td>${guestVo.name}</td>
								<td>${guestVo.regDate}</td>
								<td><button type="button" onclick="">삭제</button></td>
							</tr>
							<tr>
								<td colspan=4>${guestVo.content}</td>
							</tr>	
						</table>
					   <br/>
					</c:forEach> 
					
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
	<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- footer -->
		
	</div> <!-- /container -->

</body>
</html>