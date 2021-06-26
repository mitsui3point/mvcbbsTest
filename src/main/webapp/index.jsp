<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Main</title>
	<!-- css -->
	<!-- font-awesome https://cdnjs.com/libraries/font-awesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog==" crossorigin="anonymous"/>
	<!-- js -->
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
</head>
<body>
	<p>web.xml welcome file index.jsp 수정요망</p>
	<c:catch>
		<c:choose>
			<c:when test="${empty authInfo }">
				<ul>
					<li>
						<a href="/register/step1"><i class="fas fa-user-plus"></i>회원가입</a>
					</li>
					<li>
						<a href="/login/login"><i class="fas fa-sign-in-alt"></i>로그인</a>
					</li>
					<li>
						<a href="/board/boardList"><i class="fas fa-sign-in-alt"></i>게시판</a>
					</li>
				</ul>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${authInfo.grade eq '1' }">
						<li>
							<p><c:out value="관리자 ${authInfo.name }님, 환영합니다."/></p>
						</li>
						<li>
							<a href="/board/boardList"><i class="fas fa-sign-in-alt"></i>게시판</a>
						</li>
						<li>
							<a href="/login/logout">로그아웃</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<p><c:out value="${authInfo.name }님, 반갑습니다."/></p>
						</li>
						<li>
							<a href="/board/boardList"><i class="fas fa-sign-in-alt"></i>게시판</a>
						</li>
						<li>
							<a href="/login/logout">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:catch>
</body>
</html>