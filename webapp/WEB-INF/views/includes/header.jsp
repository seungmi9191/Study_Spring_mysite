<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<div id="header">
	<h1>
		<a href="${pageContext.request.contextPath}/main">MySite</a>
	</h1>
	<ul>

		<c:choose>
			<c:when test="${authUser eq null}">
				<!-- 로그인 전 -->
				<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/joinform">회원가입</a></li>
			</c:when>
			<c:otherwise>
			<!-- 로그인 후 -->
				<li><a href="${pageContext.request.contextPath}/user/modifyform">회원정보수정</a></li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li> 
				<li><font color="green"><b>${authUser.name}</font></b>님</li>
			</c:otherwise>
		</c:choose>
		
	</ul>
</div>
