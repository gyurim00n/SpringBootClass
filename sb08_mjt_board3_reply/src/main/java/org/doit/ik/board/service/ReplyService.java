package org.doit.ik.board.service;

import java.util.List;

import org.doit.ik.board.dto.ReplyDTO;
import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;

public interface ReplyService {

	//[1] ´ñ±Û µî·Ï
	Long register(ReplyDTO replyDTO);

	//[2] ´ñ±Û ¸ñ·Ï
	List<ReplyDTO> getList(Long bno);

	//[3] ´ñ±Û ¼öÁ¤
	void modify(ReplyDTO replyDTO);

	//[4] ´ñ±Û »èÁ¦
	void remove(Long rno);

	//[5] dto -> Entity º¯È¯
	default Reply dtoToEntity(ReplyDTO replyDTO) {
		Board board = Board.builder().bno(replyDTO.getBno()).build();

		Reply reply = Reply.builder()
				.rno(replyDTO.getRno())
				.text(replyDTO.getText())
				.replyer(replyDTO.getReplyer())
				.board(board)
				.build();
		return reply;      
	}
	
	//[6] Entity -> dto º¯È¯
	default ReplyDTO entityToDTO(Reply reply) {    
		ReplyDTO replyDTO = ReplyDTO.builder()
				.rno(reply.getRno())
				.text(reply.getText())
				.replyer(reply.getReplyer())
				.regDate(reply.getRegDate())
				.modDate(reply.getModDate())
				.build();
		return replyDTO;
	}

}
