package org.doit.ik.guestbook.service;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.springframework.stereotype.Service;

@Service
public interface GuestbookService {
	//자연적으로 호출되기에 DTO <-> EN 함수를 여기에 만든다.
	//[1] 방명록 목록
	PageResultDTO<GuestbookDTO, Guestbook>getList(PageRequestDTO pageRequestDTO);

	//[2] 방명록 쓰기
	Long register(GuestbookDTO guestbookDTO);

	//[3] 방명록 수정


	// ModelMapper라이브러리(http://modelmapper.org), MapStruct(https://mapstruct.org) 등 이용
	//default 함수는 인터페이스에 추가...
	// DTO -> Entity 변환 메서드
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();      
		return entity;
	}

	// Entity -> DTO 변환 메서드
	default GuestbookDTO entityToDto(Guestbook entity) {
		GuestbookDTO dto = GuestbookDTO.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();      
		return dto;
	}


	//[4] 방명록 삭제


	//[5] 방명록 검색

}
