package com.mvcbbs.common.paging;
/**
 * 페이징에 관련된 버튼들을 만드는 클래스
 * 페이지에 페이징 버튼들을 만들기 위한 계산 클래스라고 생각하면 된다.
 * 
 * @author adm
 *
 */
public class PageMaker {
	private Criteria cri;			// page: 현재 페이지 번호, perPageNum : 한 페이지당 보여줄 게시글의 갯수
	private int totalCount;			// 총 게시글 수
	private int startPage;			// 첫페이지 cf)11 ~ 20 일 경우에서 11 
	private int endPage;			// 마지막페이지 cf)11 ~ 20 일 경우에서 20
	private boolean prev;			// 이전버튼
	private boolean next;			// 다음버튼
	private int displayPageNum = 5;	// 화면 하단에 보여지는 페이지 버튼의 수  cf)11 ~ 20 일 경우 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 총 10개 노출, 11 ~ 15 일 경우 11, 12, 13, 14, 15 총 5개 노출ㅁ
	private void calcData() {
		// 끝 페이지 번호 = (현재 페이지 번호 / 화면에 보여질 페이지 갯수).ceil * 화면에 보여질 페이지 갯수
		// ceil : 소숫점이 나오면 올림처리
		/**
		 *	현재 페이지 번호		페이지 번호의 갯수		계산식					끝 페이지 번호
		 *	1				10					Math.ceil(1/10) * 10	10
		 *	3				10					Math.ceil(3/10) * 10	10
		 *	13				10					Math.ceil(13/10) * 10	20
		 *	23				20					Math.ceil(23/20) * 20	40
		 */
		endPage = (int) (Math.ceil( cri.getPage() / (double) displayPageNum ) * displayPageNum);
		// 첫 페이지 번호 = 끝 페이지 번호
		/**
		 * 끝 페이지 번호		페이지 번호의 갯수		계산식			시작 페이지 번호
		 * 10				10					(10-10) + 1		1
		 * 30				10					(30-20) + 1		11
		 * 40				20					(40-20) + 1		21
		 * 20				5					(20-5) + 1		16
		 */
		startPage = (endPage - displayPageNum)+ 1;
		if (startPage <= 0) startPage = 1;
		// 끝 페이지 번호 2; (총 게시글 수/ 한 페이지당 보여줄 게시글의 갯수).ceil ; 끝 페이지 번호 18 일 경우 endPage = 20이 할당되어있기 때문에 바꾸어줘야함
		int tempEndPage = (int) (Math.ceil( totalCount / (double) cri.getPerPageNum() ) );
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		// 이전 버튼은 시작 페이지 번호가 1이 아니면 생기면 된다.
		prev = startPage == 1 ? false : true;
		/**
		 *  끝 페이지 번호		페이지당 게시글 수		총 게시글 수		계산식			다음 버튼 생성 여부
		 *  7				10					65				7 * 10 < 65		false
		 *  10				10					100				10 * 10 < 100	false
		 *  10				10					127				10 * 10 < 107	true
		 *  20				10					260				20 * 10 < 260	true
		 */
		next = totalCount > endPage * cri.getPerPageNum() ? true : false;
//		next = endPage != tempEndPage ? true : false;
		
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
}
