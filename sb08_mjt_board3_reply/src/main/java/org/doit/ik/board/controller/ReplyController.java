package org.doit.ik.board.controller;

import java.util.List;

import org.doit.ik.board.dto.ReplyDTO;
import org.doit.ik.board.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("")	//JSON.stringify(reply) -> dto 변환 필요
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
		log.info("😘😘 ReplyController.register()...replyDTO" + replyDTO);
		Long rno = this.replyService.register(replyDTO);
		return new ResponseEntity<>(rno, HttpStatus.OK);
	}
	
	@DeleteMapping("/{rno}")	//JSON.stringify(reply) -> dto 변환 필요
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("😘😘 ReplyController.remove()...rno" + rno);
		this.replyService.remove(rno);
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	@PutMapping("/{rno}")	//JSON.stringify(reply) -> dto 변환 필요
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){
		log.info("😘😘 ReplyController.modify()...replyDTO" + replyDTO);
		this.replyService.modify(replyDTO);
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
}
