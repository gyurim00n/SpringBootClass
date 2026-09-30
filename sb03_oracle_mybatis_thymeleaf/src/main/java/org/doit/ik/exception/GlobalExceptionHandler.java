package org.doit.ik.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
   
   /*
     * 1. 업무 예외
     */
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException e, Model model) {
        log.warn("BusinessException : {}", e.getMessage());
        model.addAttribute("message", e.getMessage());
        return "error/error";
    }

    /*
     * 2. DB 관련 예외
     */
    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException( DataAccessException e, Model model) {
        log.error("Database Error", e);
        model.addAttribute("message", "데이터베이스 처리 중 오류가 발생했습니다." );
        return "error/error";
    }

    /*
     * 3. 그 외 예상하지 못한 예외
     */
   @ExceptionHandler(Exception.class)
   public String handleException(Exception e, Model model) {
      log.error("🔥 예외 발생!", e);
      model.addAttribute("message", "서버에서 오류가 발생했습니다.");
      model.addAttribute("exception", e);
      return "error/error";
   }

}
