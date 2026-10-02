package org.doit.ik.guestbook.service;

import java.util.function.Function;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor

public class GuestbookServiceImpl implements GuestbookService{
	private final GuestbookRepository guestbookRepository;

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

	@Override
	public Long register(GuestbookDTO guestbookDTO) {
		log.info("~~~~~GuestbookServiceImpl.register()");
		Guestbook entity = this.dtoToEntity(guestbookDTO);
		this.guestbookRepository.save(entity);
		return entity.getGno();
	}


}
