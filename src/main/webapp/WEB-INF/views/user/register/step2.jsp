<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/front/header.jsp" %>
	<!-- // header -->
	<!-- contents : ajax 로 수정할 것.-->
	<div class="">
		<div class="">
			<!-- validator -->
			<form:form role="form" action="/register/step3" modelAttribute="registerRequestVO" method="post">
			<!-- hibernate -->
<%-- 			<form:form role="form" action="/register/step3h" modelAttribute="registerRequestHibernateVO" method="post"> --%>
				<div class="">
					<span class=""><i class="">√</i>아이디</span>
					<form:input type="text" class="" placeholder="ID" path="id"/>
					<form:errors path="id"/>
				</div>
				<div class="">
					<span class=""><i class="">√</i>이메일</span>
					<form:input type="text" class="" placeholder="Email" path="email"/>
					<form:errors path="email"/>
				</div>
				<div class="">
					<span class=""><i class="">√</i>이름</span>
					<form:input type="text" class="" placeholder="Name" path="name"/>
					<form:errors path="name"/>
				</div>
				<div class="">
					<span class=""><i class="">√</i>비밀번호</span>
					<form:input type="text" class="" placeholder="Password" path="pw"/>
					<form:errors path="pw"/>
				</div>
				<div class="">
					<span class=""><i class="">√</i>비밀번호 확인</span>
					<form:input type="text" class="" placeholder="Password Check" path="checkPw"/>
					<form:errors path="checkPw"/>
				</div>
				<button type="submit">가입하기</button>
				<button type="reset">취소하기</button>
			</form:form>
		</div>
	</div>
	<!-- // contents -->
	<!-- footer -->
	<%@include file="/WEB-INF/views/common/front/footer.jsp" %>
	<!-- // footer -->