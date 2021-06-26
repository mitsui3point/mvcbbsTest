<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/front/header.jsp" %>
	<!-- // header -->
	<!-- contents -->
	<div class="">
		로그인 성공 !${authInfo.name }님, 반갑습니다.
		<ul>
			<li><a href="/login/logout">로그아웃</a></li>
		</ul>
		<ul>
			<li><a href="/">메인으로</a></li>
		</ul>
	</div>
	<!-- // contents -->
	<!-- footer -->
	<%@include file="/WEB-INF/views/common/front/footer.jsp" %>
	<!-- // footer -->
</html>