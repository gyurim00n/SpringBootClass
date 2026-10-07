package org.doit.ik.board.service;

import java.util.function.Function;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.dto.PageResultDTO;
import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Member;
import org.doit.ik.board.repository.BoardRepository;
import org.doit.ik.board.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoadServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	
	
	//[3-2]게시글 목록
		@Override
		public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
			
			log.info("getList..." + pageRequestDTO);
		
			//searchPage
			Page<Object []> result = this.boardRepository.searchPage(
					pageRequestDTO.getType()
					, pageRequestDTO.getKeyword()
					, pageRequestDTO.getPageable(Sort.by("bno").descending()));
					
			
		
			Function<Object[], BoardDTO> fn = (en -> entityToDTO( (Board)en[0], (Member)en[1], (Long)en[2] ));

			return new PageResultDTO<>(result, fn);
		}
	/*
	//[3]게시글 목록
	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		//EN: Object[] - Member, Board, Reply 엔티티 
		log.info("getList..." + pageRequestDTO);
	
		Page<Object []> result = this.boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
		
		//entity -> DTO 변환
		//Page<Object []> -> PageResultDTO 변환
		Function<Object[], BoardDTO> fn = (en -> entityToDTO( (Board)en[0], (Member)en[1], (Long)en[2] ));

		return new PageResultDTO<>(result, fn);
	}
	*/
	//[4]게시글 등록
	@Override
	public Long register(BoardDTO boardDTO) {
		log.info("😘 BoardServiceImpl.register()..." + boardDTO);
		//BoardDTO-> Board 변환
		Board entity = this.dtoToEntity(boardDTO);
		this.boardRepository.save(entity);
		log.info("😘 게시글 " + entity.getBno() + "번 등록 완료!");
		return entity.getBno();
	}
	
	//[5]게시글 상세보기
	@Override
	public BoardDTO get(Long bno) {
		log.info("😘 BoardServiceImpl.get()..." +bno);
		//this.boardRepository.findById(bno);
		Object result = this.boardRepository.getBoardByBno(bno);
		Object [] arr = (Object []) result;
		
		return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}
	
	//[6]게시글 수정
	@Override
	public void modify(BoardDTO boardDTO) {
		//					findById();
		Board entity = this.boardRepository.getReferenceById(boardDTO.getBno()); // 지연로딩 방식으로 처리됨 . entity 반환
		
		log.info("😘 BoardServiceImpl.modify()..." + entity);
		if(entity != null) {
			//제목, 내용을 엔티티 수정
			entity.changeTitle(boardDTO.getTitle());
			entity.changeContent(boardDTO.getContent());
			//BoardDTO-> Board 변환
			
			this.boardRepository.save(entity);
		}
	
	}

	//[7]게시글 삭제 + 댓글도 삭제
	@Transactional//두 작업이라 묶어줘야한다.
	@Override
	public void removeWithReplies(Long bno) {
		// 주의 삭제순서 (댓글의 fk가 board로부터 옴)
		//1) 댓글 먼저 삭제
		this.replyRepository.deleteByBno(bno);
		
		//2) 해당 게시글 삭제
		this.boardRepository.deleteById(bno);
		
	
		
	}

}
