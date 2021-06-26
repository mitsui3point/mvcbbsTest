<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/front/header.jsp" %>
	<!-- // header -->
		<script type="text/javascript">
			// 이벤트
			$(document).ready(function() {
				
				// 글쓰기 버튼 클릭
				$('#write').on('click',function() {
					// 글쓸 내용 validation
					var validate = fnValidation();
					if(validate) {
						/* formData 일 경우
							var url = '<c:url value="/board/boardInsertForm"/>';
							var param = new FormData($('#insertFrm')[0]);
							cmmAjaxClosureTest(url,param).done(function(data) {...}).fail(function(){});
							*/
						/* @RequestParam(value = "title") String title.. 일 경우
							var url = '<c:url value="/board/boardInsertReqParam"/>';
							var param = frmToObj($('#insertFrm'));
							*/
						// @RequestBody Map<String, Object> 일 경우
						// form > JSON Str paramData setting 
						var url = '<c:url value="/board/boardInsert"/>';
						var param = frmToJSONStr($('#insertFrm'));
						cmmAjax(url,param).done(function(data) {
							console.log(data);
							var code = data.resultCode;
							if(code == '100') {
								alert('게시글이 등록되었습니다.');
								// 게시글 목록 페이지 호출
								fnListView();
							} else {
								alert('게시글 등록을 실패했습니다.');
							}
						}).fail(function() {
							alert('게시글 등록 도중 오류가 발생했습니다.');
							// 게시글 쓰기 페이지 호출
							fnWriteView();
						});
					}
				});
				
				// 목록 버튼 클릭
				$('#goList').on('click',function() {
					// 목록 페이지 호출
					fnGoList();
				});
				
			});
			
			// 게시글 쓰기 페이지
			function fnWriteView() {
				location.href = '<c:url value="/board/boardWrite"/>';
			}
			
			// 게시글 목록 페이지
			function fnListView() {
				location.href = '<c:url value="/board/boardList"/>';
			}
			
			// validation
			function fnValidation() {
				var titleVal = $('#title').val();
				var contentsVal = $('#contents').val();
				if(isEmpty(titleVal)) {
					alert('제목을 입력해주세요.');
					return false;
				}
				if(isEmpty(contentsVal)) {
					alert('내용을 입력해주세요.');
					return false;
				}
				return true;
			}
			
			// 목록 페이지
			function fnGoList() {
				location.href = '<c:url value="/board/boardList"/>';
			}
			
		</script>
		<!-- contents -->
		<div class="contents">
			<form id="insertFrm" name="insertFrm"  method="post" enctype="multipart/form-data">
				<div>
					<label for="title">제목</label>
					<input type="text" id="title" name="title" class="">
				</div>
				<div>
					<label for="creaId">작성자</label>
					<input type="text" id="creaId" name="creaId" value="<c:out value="${creaId}"/>" readonly="readonly"/>
				</div>
				<div>
					<label for="contents">내용</label>
					<textarea id="contents" name="contents" class=""></textarea>
				</div>
			</form>
			<div class="btn-area">
				<button id="write" class="" type="button" >글쓰기</button>
				<button id="goList" class="" type="button" >목록</button>
			</div>
		</div>
		<!-- // contents -->
	<!-- footer -->
	<%@include file="/WEB-INF/views/common/front/footer.jsp" %>
	<!-- // footer -->
</html>