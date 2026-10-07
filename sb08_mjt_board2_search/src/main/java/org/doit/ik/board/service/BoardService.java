package org.doit.ik.board.service;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.dto.PageResultDTO;
import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Member;

public interface BoardService {
	
	//(1) dto- > entity 변환 메서드
	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder().email(dto.getWriterEmail()).build();
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		return board;      
	}
	//(2) entity- > dto 변환 메서드
	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {    
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())
				.build();
		return boardDTO;
	}
	
	//[3]게시글 목록
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	//[4]게시글 등록
	Long register(BoardDTO boardDTO);
	
	//[5]게시글 상세보기
	BoardDTO get(Long bno);
	
	//[6]게시글 수정
	void modify(BoardDTO boardDTO);
	
	//[7]게시글 삭제 + 댓글도 함께 삭제
	void removeWithReplies(Long bno);
}
