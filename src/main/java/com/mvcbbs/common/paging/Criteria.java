package com.mvcbbs.common.paging;

/**
 * 게시글 조회 쿼리에 전달될 파라미터를 담은 VO클래스
 * 
 * 1
 * 2
 * 현재 페이지의 게시글 시작 번호 = (현재 페이지 번호 - 1) * 페이지당 보여줄 게시글 갯수
 * getPerPageNum = (this.page - 1) * perPageNum
 * 
 *  현재 페이지 번호		페이지당 보여줄 게시글 수		계산식		게시글 시작 행 번호
 *  5				10						(5-1)*10	40 (0~39 : 4page)
 *  3				5						(3-1)*5		10 (0~9 : 2page)
 *  15				10						(15-1)*10	140 (0~139 : 14page)
 * @author adm
 */
public class Criteria {
	private int page;		// 현재 페이지 번호
	private int perPageNum;	// 한 페이지당 보여줄 게시글의 갯수
	private String schCond;	// 검색 필드 지정
	private String schKwrd;	// 검색 키워드
	// 특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
	private int getPageStart() {
		return (this.page -1) * perPageNum;
	}
	/**
	 생성자
	 최초로 게시판 목록에 들어왔을떄는 기본 세팅을 해야 한다.
	 왜냐하면 페이징을 처리하기 위해서는 
	 
	 현재 페이지 번호
	 와 
	 페이지당 게시글 수
	 가 필요한데
	 
	 처음 게시판에 들어오게 되면 
	 두 개의 정보를 가져올 방법이 없기 때문에
	 기본 생성자를 통해 기본값을 세팅하도록 하자.
	 
	 현재 페이지는 1페이지로, 페이지당 보여줄 게시글의 수를 10개로 기본 셋팅해 두었다.
	 */
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		// 음수가 들어올 경우
		if(page <= 0) {
			this.page = 1;
		// 음수가 아닌 경우
		} else {
			this.page = page;
		}
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;
		// setter 파라미터에 pagecount = 10 과 다른 값을 입력한 경우
		if(pageCount != cnt) {
			this.perPageNum = cnt;
		// setter 파라미터에 pagecount = 10 입력한 경우
		} else {
			this.perPageNum = pageCount;
		}
	}
	public String getSchCond() {
		return schCond;
	}
	public void setSchCond(String schCond) {
		this.schCond = schCond;
	}
	public String getSchKwrd() {
		return schKwrd;
	}
	public void setSchKwrd(String schKwrd) {
		this.schKwrd = schKwrd;
	}
}
