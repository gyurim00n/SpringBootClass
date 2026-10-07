package org.doit.ik.board.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
	
	// 여러 엔티티 -> 검색 메서드 선언
	Page<Object []> searchPage(String type, String keyword, Pageable pageable);
}
