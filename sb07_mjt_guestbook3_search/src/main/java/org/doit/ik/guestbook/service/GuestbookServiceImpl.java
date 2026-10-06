package org.doit.ik.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.entity.QGuestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor

public class GuestbookServiceImpl implements GuestbookService{
	private final GuestbookRepository guestbookRepository;
	/*
	//[1] 방명록 조회
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
		log.info("~~~~~GuestbookServiceImpl.getList()");
		Sort sort= Sort.by(Guestbook::getGno).descending(); 
		Pageable pageable = pageRequestDTO.getPageable(sort);
		Page<Guestbook> result =  this.guestbookRepository.findAll(pageable);
		//Page<Guestbook> result  -> PageResultDTO<>
		Function<Guestbook, GuestbookDTO> fn = entity -> entityToDto(entity);
		
		return new PageResultDTO<>(result, fn);
	}
 	*/
	//[1-2] 검색 관련 메서드 추가 : 동적 WHERE 절 검색 구문
	private BooleanBuilder getSearchPredicate(PageRequestDTO requestDTO){
		//Querydsl 에서 여러 검색 조건을 동적으로 조립할때 사용하는 객체(WHERE 절)
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		String type = requestDTO.getType();
		String keyword = requestDTO.getKeyword();
		
		//Q클래스 객체
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		//WHERE gno > 0 조건 추가
		BooleanExpression expr1= qGuestbook.gno.gt(0L);
		booleanBuilder.and(expr1);
		
		if(type==null || type.trim().length()==0){
			return booleanBuilder;
		}
		
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		//검색조건: t c w tc tcw
		String [] typeArr =  type.split("");
		for(String t : typeArr) {
			if(t.equals("t")) {
				BooleanExpression exprTitle = qGuestbook.title.contains(keyword);
				conditionBuilder.or(exprTitle);
			}else if(t.equals("c")){
				BooleanExpression exprContent = qGuestbook.content.contains(keyword);
				conditionBuilder.or(exprContent);
			}else if(t.equals("w")){
				BooleanExpression exprWriter = qGuestbook.writer.contains(keyword);
				conditionBuilder.or(exprWriter);
			}
			
		}//foreach
		
		booleanBuilder.and(conditionBuilder);
		//WHERE gno > 0
		//AND 
		//(title LIKE '%%' OR content LIKE '%%')
		return booleanBuilder;
	}
	
	//[1-3] 방명록 조회
		@Override
		public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
			log.info("~~~~~GuestbookServiceImpl.getList()");
			Sort sort= Sort.by(Guestbook::getGno).descending(); 
			Pageable pageable = pageRequestDTO.getPageable(sort);
			//동적으로 생성된 검색 조건 :WHERE
			BooleanBuilder booleanBuilder = this.getSearchPredicate(pageRequestDTO);
			
			
			Page<Guestbook> result =  this.guestbookRepository.findAll(booleanBuilder, pageable);
			//Page<Guestbook> result  -> PageResultDTO<>
			Function<Guestbook, GuestbookDTO> fn = entity -> entityToDto(entity);
			return new PageResultDTO<>(result, fn);
		}
	
	//[2] 방명록 등록
	@Override
	public Long register(GuestbookDTO guestbookDTO) {
		log.info("~~~~~GuestbookServiceImpl.register()");
		Guestbook entity = this.dtoToEntity(guestbookDTO);
		this.guestbookRepository.save(entity);
		return entity.getGno();
	}

	//[3] 방명록 상세보기
	@Override
	public GuestbookDTO read(Long gno) {
		log.info("~~~~~GuestbookServiceImpl.read()..." + gno);
		Optional<Guestbook> result =  this.guestbookRepository.findById(gno);
		//[1] 예외 발생
		//Guestbook entity = result.orElseThrow(()-> new RuntimeException("존재하지 않는 방명록."));
		//return this.entityToDto(entity);
		
		//[2] null 반환...
		return result.isPresent() ? this.entityToDto(result.get()): null ;
	}

	//[4-2]방명록 수정
	@Override
	public void modify(GuestbookDTO guestbookDTO) {
		log.info("~~~~~GuestbookServiceImpl.modify()..." + guestbookDTO.getGno());
		
		
		Long gno = guestbookDTO.getGno();
		Optional<Guestbook> result =  this.guestbookRepository.findById(gno);
		if(result.isPresent()) {
			//GuestbookDTO => Entity
			Guestbook entity = result.get();
			
			//제목, 내용 수정
			entity.changeTitle(guestbookDTO.getTitle());
			entity.changeContent(guestbookDTO.getContent());
			
			this.guestbookRepository.save(entity);
		}//if
		
	}
	
	//[5]방명록 삭제
	@Override
	public void remove(long gno) {
		log.info("~~~~~GuestbookServiceImpl.remove()..." + gno);
		this.guestbookRepository.deleteById(gno);
		
	}


}
