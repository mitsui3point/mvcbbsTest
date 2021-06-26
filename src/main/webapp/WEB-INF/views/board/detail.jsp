<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/front/header.jsp" %>
	<!-- // header -->
		<script type="text/javascript">
			$(document).ready(function() {
				
				// 목록 버튼 클릭
				$('#goListBtn').on('click',function() {
					// 글쓰기 수정 화면 이동
					fnListView();
				});
				
				// 글 수정 버튼 클릭
				$('#updateBtn').on('click',function() {
					var loginId = '<c:out value="${authInfo.id}" />';
					var creaId = '<c:out value="${boardInfo.creaId}" />';
					// 로그인한 경우
					if(loginId != '') {
						// 글쓴이와 로그인 아이디가 일치할 경우
						if(loginId == creaId) {
							// 글쓰기 수정 화면 이동
							var idx = '<c:out value="${boardInfo.idx}" />';// 글번호
							location.href = '<c:url value="/board/boardUpdate?idx='+idx+'"/>';
						// 글쓴이와 로그인 아이디가 불일치할 경우
						} else {
							alert('접근하신 아이디와 글쓴이가 일치하지 않습니다.');
						}
					// 로그인 하지 않았을 경우
					} else {
						alert('작성자 아이디로 로그인 후 이용이 가능합니다.');
						location.href = '<c:url value="/login/login"/>';
					}
				});
				
				// 글쓰기 삭제 버튼 클릭
				$('#deleteBtn').on('click',function() {
					var url = '<c:url value="/board/boardDelete"/>';
					var param = frmToJSONStr($('#deleteFrm'));
					cmmAjax(url,param).done(function(data) {
						console.log(data);
						var code = data.resultCode;
						// 성공
						if(code == '100') {
							alert('게시글이 삭제되었습니다.');
							// 게시글 목록 페이지 호출
							fnListView();
						// 로그인 하지 않았을 경우
						} else if (code == '-10') {
							alert('게시글 삭제 권한이 없습니다. 로그인 후 삭제 가능합니다.');
						// 작성자가 아닐 경우
						} else if (code == '-20') {
							alert('게시글 삭제 권한이 없습니다. 게시글 작성자만 삭제가 가능합니다.');
						// exception
						} else {
							alert('게시글 삭제를 실패했습니다.');
						}
					}).fail(function() {
						alert('게시글 삭제 도중 오류가 발생했습니다.');
					});
				});
				
			});
			
			// 게시글 목록 페이지
			function fnListView() {
				var frm = $('#goListFrm')[0];
				frm.action = '<c:url value="/board/boardList"/>';
				frm.submit();
			}
			
		</script>
		<!-- contents -->
		<div class="contents">
				<table>
				<caption>게시판</caption>
					<tbody>
						<tr>
							<td width="20%">idx</td>
							<td><c:out value="${boardInfo.idx}"/></td>
						</tr>
						<tr>
							<td width="20%">title</td>
							<td><c:out value="${boardInfo.title}"/></td>
						</tr>
						<tr>
							<td width="20%">hitCnt</td>
							<td><c:out value="${boardInfo.hitCnt}"/></td>
						</tr>
						<tr>
							<td width="20%">contents</td>
							<td><c:out value="${boardInfo.contents}"/></td>
						</tr>
						<tr>
							<td width="20%">creaDate</td>
							<td><c:out value="${boardInfo.creaDate}"/></td>
						</tr>
						<tr>
							<td width="20%">creaId</td>
							<td><c:out value="${boardInfo.creaId}"/></td>
						</tr>
					</tbody>
				</table>
			<div class="btn-area">
				<button id="goListBtn" class="" type="button" >목록으로</button>
				<button id="updateBtn" class="" type="button" >글수정</button>
				<button id="deleteBtn" class="" type="button" >삭제</button>
			</div>
		</div>
		<!-- 목록이동 form -->
		<form id="goListFrm" name="goListFrm">
			<input type="hidden" id="schCond" name="schCond" value="${pagingInfo.schCond}">
			<input type="hidden" id="schKwrd" name="schKwrd" value="${pagingInfo.schKwrd}">
			<input type="hidden" id="page" name="page" value="${pagingInfo.page}">
		</form>
		<!-- //목록이동 form -->
		<!-- 게시글 삭제 form -->
		<form id="deleteFrm" name="deleteFrm">
			<input type="hidden" id="idx" name="idx" value="${boardInfo.idx}">
			<input type="hidden" id="creaId" name="creaId" value="${boardInfo.creaId}">
		</form>
		<!-- //게시글 삭제 form -->
		<!-- // contents -->
	<!-- footer -->
	<%@include file="/WEB-INF/views/common/front/footer.jsp" %>
	<!-- // footer -->
</html>