<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/front/header.jsp" %>
	<!-- // header -->
		<!-- contents -->
		<div class="">
			<div class="">
				<div class="">
					<h4>이용약관</h4>
					<div class="">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla augue. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
					</div>
					<br/>
					<h4>개인정보</h4>
					<div class="">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla augue. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
					</div>
					<br/>
					<form role="form" action="/register/step2" method="post">
						<div class="">
							<label class="">
								<input type="checkbox" name="agree" value="true"> 동의
							</label>
						</div>
						<button type="submit" class=""> 다음 단계 </button>
					</form>
				</div>
			</div>
		</div>
		<!-- // contents -->
	<!-- footer -->
	<%@include file="/WEB-INF/views/common/front/footer.jsp" %>
	<!-- // footer -->
</html>