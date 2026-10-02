package org.doit.ik.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageResultDTO<DTO, EN> {
	
	private int totalPage;
	private int page;
	private int size;
	private int start, end;
	private boolean prev, next;
	private List<Integer> pageList;	//[1] 2 3 4 5 ....
	
	private List<DTO> dtoList;	//pagesize만큼의 길이의 dto를 갖고 있는 리스트 
	//Page<Guestbook> -> List<GuestbookDTO> 변환....		생성자
	
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
		this.dtoList = result.stream()
						.map(fn)
						.collect(Collectors.toList());
		this.totalPage =result.getTotalPages();
		
		makePageList(result.getPageable());
	}
	
	private void makePageList(Pageable pageable) {
		int pageBlock = 10;
		this.page = pageable.getPageNumber() + 1;	//1, 2, ...
		this.size = pageable.getPageSize();
		
		
		int currentBlock = (this.page-1)/ pageBlock;
		this.start = currentBlock * this.size   + 1 ;
		this.end = this.start + this.size - 1;
		 if (end >  this.totalPage)      end = this.totalPage;
		 
		 this.prev = start > 1;
		 this.next = end < totalPage;
		 
		 this.pageList = IntStream.rangeClosed(start, end)		//IntStream
				 					.boxed()					//Stream<Integer>
				 					.collect(Collectors.toList());	//List<Integer>
	}
	
	

}
	// 2) 페이징 처리
	/*
    int pageBlock = 10; 
    int currentBlock = (pageNumber-1)/ pageBlock;
    long totalPages = mPage.getTotalPages(); 
    
    int startPage = currentBlock * pageBlock + 1;
    int endPage = startPage  + pageBlock -1;
    if (endPage > mPage.getTotalPages())      endPage = mPage.getTotalPages();
    boolean hasPreviousBlock = startPage > 1;
    boolean hasNextBlock = endPage < totalPages;
    */