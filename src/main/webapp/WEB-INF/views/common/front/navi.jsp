<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div id="navigation">
		<div class="">
			<p>navigation</p>
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
		</div>
	</div>