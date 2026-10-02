package org.doit.ik.guestbook.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
	//http://localhost/guestbook/list?page=3&size=10&type=t&keyword=???
	
	private int page;	//현재 페이지번호
	private int size;	//한 페이지당 출력 글 수 

	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page -1 , size, sort);
	}
}
