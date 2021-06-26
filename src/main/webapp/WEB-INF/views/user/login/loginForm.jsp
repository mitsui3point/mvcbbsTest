<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/front/header.jsp" %>
	<!-- // header -->
		<script type="text/javascript">
		</script>
		<!-- contents -->
		<div class="">
			<form:form role="form" modelAttribute="loginCommandVO" action="/login/login" method="post">
				<fieldset>
				<legend>LOGIN</legend>
					<div class="">
						<form:input type="text" class="" placeholder="ID" path="id" />
						<form:errors path="id"/>
					</div>
					<div class="">
						<form:password class="" placeholder="Password" path="pw"/>
						<form:errors path="pw"/>
					</div>
					<div class="">
						<form:checkbox path="rememberId"/>아이디 기억
					</div>
					<button type="submit" class="">로그인</button>
				</fieldset>
			</form:form>
		</div>
		<!-- // contents -->
	<!-- footer -->
	<%@include file="/WEB-INF/views/common/front/footer.jsp" %>
	<!-- // footer -->
</html>