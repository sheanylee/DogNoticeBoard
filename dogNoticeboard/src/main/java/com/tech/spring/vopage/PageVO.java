package com.tech.spring.vopage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageVO {
	//페이징 관련 변수	
	private int totalCount = 0 ; //총 레코드 건수
	private int totalPage = 0 ; //전체 페이지 수
	private int pageNumber = 0 ; //보여줄 페이지 넘버(표현 가능한 페이지는 1부터 totalPage까지이다.)
	private int pageSize = 0 ; //한 페이지에 보여줄 건수
	private int beginRow = 0 ; //현재 페이지의 시작 행
	private int endRow = 0 ; //현재 페이지의 끝 행
	private int pageCount = 5 ; // 한 화면에 보여줄 페이지 링크 수 (페이지 갯수)
	private int beginPage = 0 ; //페이징 처리 시작 페이지 번호
	private int endPage = 0 ; //페이징 처리 끝 페이지 번호
	private int offset = 0 ;
	private int limit = 0 ;
	private String url = "" ; //예시 ==>  http://localhost:9191/MyServlet/list.do
	private String pagingHtml = "";//하단의 숫자 페이지 링크
	private String whatColumn = "" ; //검색 모드(작성자, 글제목, 전체 검색은 all) 등등
	private String keyword = "" ; //검색할 단어 

	public PageVO(  //리미트와 오프셋이 중요하다
			String _pageNumber, 
			String _pageSize,  //필요없음
			int totalCount,
			String url, 
			String whatColumn, 
			String keyword,
			String whologin) { //필요없음		

		if(  _pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")  ){
			System.out.println("_pageNumber:"+_pageNumber); // null
			_pageNumber = "1" ; //무조건1페이지부터 보이도록(_pageNumber == null 넘기는게 없을때)
		}
		this.pageNumber = Integer.parseInt( _pageNumber ) ; 

		if( _pageSize == null || _pageSize.equals("null") || _pageSize.equals("") ){
			_pageSize = "5" ; // 한 페이지에 보여줄 레코드 갯수
		}		
		this.pageSize = Integer.parseInt( _pageSize ) ;
		
		this.limit = pageSize ; // 한 페이지에 보여줄 레코드 갯수 limit = pageSize 똑같다

		this.totalCount = totalCount ; //멤버변수에 넣어줌.

		this.totalPage = (int)Math.ceil((double)this.totalCount / this.pageSize) ;
		// 5.0/2 =2.5 double없다면 정수2  , Math.ceil 올려라
		
		this.beginRow = ( this.pageNumber - 1 )  * this.pageSize  + 1 ;  //1p biginrow= 1 /3page biginrow= 5
		this.endRow =  this.pageNumber * this.pageSize ;
		if( this.pageNumber > this.totalPage ){   //내가선택한 페이지 3페이지 삭제하고 나니까 토탈페이지가 2페이지가 됨
			this.pageNumber = this.totalPage ;
		}
		
		this.offset = ( pageNumber - 1 ) * pageSize ;  //몇개를 건너뛰어야하나, 건너뛸 레코드 갯수가 들어간다
		if( this.endRow > this.totalCount ){
			this.endRow = this.totalCount  ;
		} 

		this.beginPage = ( this.pageNumber - 1 ) / this.pageCount * this.pageCount + 1  ;
		this.endPage = this.beginPage + this.pageCount - 1 ;
		System.out.println("pageNumber:"+pageNumber+"/totalPage:"+totalPage);	
		
		if( this.endPage > this.totalPage ){
			this.endPage = this.totalPage ;
		}
		
		System.out.println("pageNumber2:"+pageNumber+"/totalPage2:"+totalPage);	
		this.url = url ; //  /ex/list.ab
		this.whatColumn = whatColumn ;
		this.keyword = keyword ;
		System.out.println("whatColumn:"+whatColumn+"/keyword:"+keyword);
		
		this.pagingHtml = getPagingHtml(url) ; //url을 밑으로 넘겨준다.
	
	} //생성자 
	
	private String getPagingHtml( String url ){ //페이징 문자열을 만든다. pagingHtml 페이징 문자열: 123 다음 맨끝 이런거
		System.out.println("getPagingHtml url:"+url);  // /ex/list.ab
		
		String result = "" ;
		String added_param = "&whatColumn=" + whatColumn + "&keyword=" + keyword ; 
		
		if (this.beginPage != 1) { // 앞쪽, pageSize:한 화면에 보이는 레코드 수   시작페이지가 1페이지가 아닐때 보이게하라
			result += "<li class=\"page-item\"><a class=\"page-link\" href='" + url  
					+ "?pageNumber=" + ( 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>&larr;</a></li>" ;
			result += "<li class=\"page-item\"><a class=\"page-link\" href='" + url 
					+ "?pageNumber=" + (this.beginPage - 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>&laquo;</a><li>" ;
		}
		
		//가운데
		for (int i = this.beginPage; i <= this.endPage ; i++) {
			if ( i == this.pageNumber ) {
				result += "<li class=\"page-item active\"><a class=\"page-link\">" + i + "</a></li>"	;
						
			} else {
				result += "<li class=\"page-item\"><a class=\"page-link\" href='" + url   
						+ "?pageNumber=" + i + "&pageSize=" + this.pageSize 
						+ added_param + "'>" + i + "</a></li>" ;
				
			}
		}
		System.out.println("result:"+result);  
		System.out.println();
		
		if ( this.endPage != this.totalPage) { // 뒤쪽
			
			result += "<li class=\"page-item\"><a class=\"page-link\" href='" + url  
					+ "?pageNumber=" + (this.endPage + 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>&raquo;</a></li>" ;
			
			result += "<li class=\"page-item\"><a class=\"page-link\" href='" + url  
					+ "?pageNumber=" + (this.totalPage ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>&rarr;</a></li>" ;
		}		
		System.out.println("result2:"+result);
		
		return result ;
	}	
}


