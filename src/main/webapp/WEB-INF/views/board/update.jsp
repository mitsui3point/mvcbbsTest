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
				
				// 글쓰기 버튼 클릭
				$('#updateBtn').on('click',function() {
					var url = '<c:url value="/board/boardUpdate"/>';
					var param = frmToJSONStr($('#updateFrm'));
					var validate = fnValidation();
					if(validate)
					cmmAjax(url,param).done(function(data) {
						console.log(data);
						var code = data.resultCode;
						// 성공
						if(code == '100') {
							alert('게시글이 수정되었습니다.');
							// 게시글 목록 페이지 호출
							fnListView();
						// 로그인 하지 않았을 경우
						} else if (code == '-10') {
							alert('게시글 수정 권한이 없습니다. 로그인 후 수정 가능합니다.');
						// 작성자가 아닐 경우
						} else if (code == '-20') {
							alert('게시글 수정 권한이 없습니다. 게시글 작성자만 수정이 가능합니다.');
						// exception 코드
						} else {
							alert('게시글 수정을 실패했습니다.');
						}
					}).fail(function() {
						alert('게시글 수정 도중 오류가 발생했습니다.');
					});
				});
				
				// 글쓰기 취소 버튼 클릭
				$('#cancelBtn').on('click',function() {
					var idx = '<c:out value="${boardInfo.idx}" />';
					// 게시판 화면 이동
					location.href = '<c:url value="/board/boardDetail?idx='+idx+'"/>';
				});
				
			});
			
			// 게시글 목록 페이지
			function fnListView() {
				var frm = $('#goListFrm')[0];
				frm.action = '<c:url value="/board/boardList"/>';
				frm.submit();
			}
			
			// 게시글 목록 페이지
			function fnDetailView() {
				var frm = $('#goDetailFrm')[0];
				frm.action = '<c:url value="/board/boardDetail?idx='+frm.idx+'"/>';
				frm.submit();
			}
			
			// validation
			function fnValidation() {
				var titleVal = $('#title').val();
				if(isEmpty(titleVal)) {
					alert('제목을 입력해주세요.');
					return false;
				}
				return true;
			}
		</script>
		<!-- contents -->
		<div class="contents">
			<form id="updateFrm" name="updateFrm" method="post">
				<input id="idx" name="idx" type="hidden" value="${boardInfo.idx}">
				<input id="creaId" name="creaId" type="hidden" value="${boardInfo.creaId}">
				<table style="border: 1px solid;">
					<caption>게시판</caption>
					<tbody>
						<tr>
							<td width="20%">idx</td>
							<td><c:out value="${boardInfo.idx}"/></td>
						</tr>
						<tr>
							<td width="20%">title</td>
							<td><input id="title" name="title" type="text" value="<c:out value="${boardInfo.title}"/>"></td>
						</tr>
						<tr>
							<td width="20%">hitCnt</td>
							<td><c:out value="${boardInfo.hitCnt}"/></td>
						</tr>
						<tr>
							<td width="20%">contents</td>
							<td><textarea id="contents" name="contents" rows="5" ><c:out value="${boardInfo.contents}"/></textarea></td>
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
			</form>
			<div class="btn-area">
				<button id="goListBtn" class="" type="button" >목록으로</button>
				<button id="updateBtn" class="" type="button" >수정</button>
				<button id="cancelBtn" class="" type="button" >취소</button>
			</div>
		</div>
		<!-- 목록이동 form -->
		<form id="goListFrm" name="goListFrm">
			<input type="hidden" id="searchKwrd" name="searchKwrd">
			<input type="hidden" id="pagingIdx" name="pagingIdx">
		</form>
		<!-- //목록이동 form -->
		<!-- 상세이동 form -->
		<form id="goDetailFrm" name="goDetailFrm">
			<input type="hidden" id="idx" name="idx" value="${boardInfo.idx}">
		</form>
		<!-- //상세이동 form -->
		<!-- // contents -->
	<!-- footer -->
	<%@include file="/WEB-INF/views/common/front/footer.jsp" %>
	<!-- // footer -->
</html>