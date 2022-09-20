package com.ysh.exam.capstone.dto;


import lombok.Getter;

@Getter
public class PageParam {

	// 인스턴스생성시 원하는 기본값 설정
	private int page = 1;
	private int amount = 6;
	
    	//Mybatis Mapper에 바인딩 시 활용됨
	private int start = 0;
	
	public PageParam() {
	}
	
	public void setAmount(int amount) {
//		log.warn("setAmount");
		this.amount = amount;
		this.start = (page-1)*amount;
	}
	
	public void setPage(int page) {
//		log.warn("setPage");
		this.page = page;
		this.start = (page-1)*amount;
	}
}