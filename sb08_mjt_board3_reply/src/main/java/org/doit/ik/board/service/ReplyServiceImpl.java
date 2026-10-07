package org.doit.ik.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.doit.ik.board.dto.ReplyDTO;
import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;
import org.doit.ik.board.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(ReplyDTO replyDTO) {
		//this.replyRepository.
		return null;
	}

	@Override
	public List<ReplyDTO> getList(Long bno) {
		Board board = Board.builder().bno(bno).build();
		List<Reply> result= this.replyRepository.getRepliesByBoardOrderByRno(board);
		return result
				.stream()							//Stream<Reply>
				.map(reply -> entityToDTO(reply))	//Stream<ReplyDTO>
				.collect(Collectors.toList())		//List<ReplyDTO>
				;
	}

	@Override
	public void modify(ReplyDTO replyDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long rno) {
		// TODO Auto-generated method stub
		
	}
	
}
