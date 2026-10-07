package org.doit.ik;

import java.util.List;
import java.util.stream.IntStream;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;
import org.doit.ik.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReplyRepositoryTests {
   
   @Autowired
   private ReplyRepository replyRepository;

   @Test
   void insertReply() {
      
      IntStream.rangeClosed(1, 300).forEach( i -> {
         
         long bno = (long)(Math.random()*99)+2;  // 1~100 bno
          
         // board
         Board board = Board.builder()
               .bno(bno)
               .build();
         
         // reply
         Reply reply = Reply.builder()
               .text("reply..." + i)
                .board(board)
                .replyer("guest")
               .build();
         
         this.replyRepository.save(reply);
         
      });
      
      System.out.println("🤩🤩🤩 end. ");
      
   }
    
   @Test
   void testGetRepliesByBoardOrderByRno() {
	   Board board = Board.builder().bno(50L).build();
	   List<Reply> result = this.replyRepository.getRepliesByBoardOrderByRno(board);
	   System.out.println("-------------------------------------");
	   result.forEach(reply -> {
		   
		   System.out.println("😍😍"+ reply);
	   });
	   System.out.println("-------------------------------------");
   }
}
