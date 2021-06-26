<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/front/header.jsp" %>
	<!-- // header -->
		<script type="text/javascript">
			$(document).ready(function() {
				// 글쓰기 버튼 클릭
				$('#write').on('click',function() {
					// 글쓰기 화면 이동
					location.href = '<c:url value="/board/boardWrite"/>';
				});
				// 검색 버튼 클릭
				$('#schBtn').on('click',function() {
					// 검색
					var frm = document.schFrm;
					frm.page.value = '<c:out value="1"/>';
					frm.action = '<c:url value="/board/boardList"/>';
					frm.submit();
				});
				// 글목록 클릭
				$('.boardTitle').on('click', function(e){
					e.preventDefault();
					var frm = document.schFrm;
					frm.idx.value = $(this).attr('id').split('title')[1];
					frm.action = '<c:url value="/board/boardDetail"/>';
					frm.submit();
				})
				
				// 페이징
				$('.paging-btn').on('click', function(e) {
					var btn = $(this).attr('name');
					var frm = document.schFrm;
					frm.action = '<c:url value="/board/boardList"/>';
					if(btn == 'pageStartBtn' ) {
						frm.page.value = '<c:out value="${ pageMaker.startPage - 1 }" />';
					} else if(btn == 'pageEndBtn' ) {
						frm.page.value = '<c:out value="${ pageMaker.endPage + 1 }" />';
					} else if(btn == 'pagingBtn' ) {
						frm.page.value = $(this).attr('id').split(btn)[1];
					}
					frm.submit();
				});
				
			});
		</script>
		<!-- contents -->
		<div class="contents">
			<div class="schform-area">
				<form id="schFrm" name="schFrm">
					<input type="hidden" id="idx" name="idx">
					<input type="hidden" id="page" name="page" value="${pageInfo.page}">
					<select name="schCond" id="schCond" value="${pageInfo.schCond}">
						<option value="COND0" <c:if test="${pageInfo.schCond eq 'COND0'}">selected</c:if>>전체</option>
						<option value="COND1" <c:if test="${pageInfo.schCond eq 'COND1'}">selected</c:if>>제목</option>
						<option value="COND2" <c:if test="${pageInfo.schCond eq 'COND2'}">selected</c:if>>글쓴이</option>
					</select>
					<p>검색할 내용을 입력하세요</p>
					<input type="text" id="schKwrd" name="schKwrd" value="${pageInfo.schKwrd}"/>
				</form>
				<button id="schBtn" name="schBtn" style="display:inline;">검색</button>
			</div>
			<table>
				<caption>게시판</caption>
				<thead>
					<tr>
						<th width="10%">idx</th>
						<th>title</th>
						<th width="10%">조회수</th>
						<th width="10%">작성일</th>
						<th width="10%">작성자</th>
					</tr>
				</thead>
				<tbody> <!-- 남/여를 tbody로 구분하여 그룹핑 -->
					<c:choose>
						<c:when test="${boardList.size() != 0}">
							<c:forEach items="${boardList}" var="boardInfo"	varStatus="status">
								<tr>
									<td><c:out value="${boardInfo.idx}"/></td>
									<td><a class="boardTitle" id="title${boardInfo.idx}" style="cursor:pointer;"><c:out value="${boardInfo.title}"/></a></td>
									<td><c:out value="${boardInfo.hitCnt}"/></td>
									<td><c:out value="${boardInfo.creaDate}"/></td>
									<td><c:out value="${boardInfo.creaId}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">조회할 게시글이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div class="paging-area">
				<ul>
					<c:if test="${pageMaker.prev }">
						<li>
							<button class="paging-btn" id="pageStartBtn" name="pageStartBtn" style="cursor:pointer;"> <c:out value="<"/> </button>
						</li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage }" var="pageNum">
						<li>
							<button class="paging-btn" id="pagingBtn${pageNum}" name="pagingBtn" style="cursor:pointer;"> <c:out value="${pageNum}" /></button>
						</li>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
						<li>
							<button class="paging-btn" id="pageEndBtn" name="pageEndBtn" style="cursor:pointer;" > <c:out value=">"/> </button>
						</li>
					</c:if>
				</ul>
			</div>
			<div class="btn-area">
				<button id="write" class="" type="button" >글쓰기</button>
			</div>
		</div>
		<!-- // contents -->
	<!-- footer -->
	<%@include file="/WEB-INF/views/common/front/footer.jsp" %>
	<!-- // footer -->
</html>