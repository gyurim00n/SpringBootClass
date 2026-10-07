package org.doit.ik.board.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

//해당 어노테이션의 의미: 테이블로 생성안할거다. 
@MappedSuperclass
// 엔티티 생명주기 이벤트에 따라 자동으로 날짜/시간 등을 처리
@EntityListeners(value= AuditingEntityListener.class)
@Getter
public class BaseEntity {
	@CreatedDate	//엔티티 최초 생성 시 자동 등록일 설정)
	@Column(name="regdate", updatable =false)
	private LocalDateTime regDate;
	
	@LastModifiedDate //엔티티 업데이트 시 자동 갱신...
	@Column(name="moddate")
	private LocalDateTime modDate;
}
