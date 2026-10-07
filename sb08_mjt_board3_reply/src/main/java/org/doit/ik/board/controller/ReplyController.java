package org.doit.ik.board.controller;

import java.util.List;

import org.doit.ik.board.dto.ReplyDTO;
import org.doit.ik.board.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
	private final ReplyService replyService;
	
	//GET	/replies/board/{bno}
	@GetMapping(value="/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListBoard(@PathVariable("bno") Long bno) {
		log.info("😘😘 ReplyController.getListBoard()...bno" + bno);
		List<ReplyDTO> result = this.replyService.getList(bno);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
