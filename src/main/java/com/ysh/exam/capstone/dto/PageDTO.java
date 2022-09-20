package com.ysh.exam.capstone.dto;

import lombok.Getter;

@Getter
public class PageDTO {

	private int endPage;
	private int startPage;
	private int realEnd;
	private int total;
	
	boolean prev, next;
	
    	//앞서 작성한 PageParam 객체를 필드로 사용한다
	PageParam pageParam;

	//pageParam을 파라미터로 받아와 페이지 연산에 활용한다
	public PageDTO(PageParam pageParam, int total) {
		
		this.pageParam = pageParam;
		this.total = total;
		
		int current = pageParam.getPage();
		int amount = pageParam.getAmount();
		
        	// 페이징의 끝번호 구하기!
            	// Math.ceil은 소숫점 자리에서 올림을 한다
		this.endPage = (int)( Math.ceil(current*0.1))*10;
        
        	// 페이징의 시작번호 구하기!
        	// (현재 보이는 페이지의 끝번호) - (한 화면에 보여질 페이지 개수 - 1) 
		this.startPage = endPage-9; 
		
		this.realEnd = (int)Math.ceil(total/amount)+1;
		
        	// 실제 끝번호 보다 endPage가 큰경우 실제 번호로 대입한다
		if(realEnd < endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = current > 1;
		this.next = current < realEnd;
	}
}