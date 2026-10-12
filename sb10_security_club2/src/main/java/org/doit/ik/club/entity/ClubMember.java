package org.doit.ik.club.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ClubMember extends BaseEntity{

	@Id
	@Column(name="email", length=100)
	private String email;	//pk

	@Column(nullable=false, length=100)
	private String password;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, columnDefinition="boolean default false")
	@Builder.Default	
	//개발자가 fromSocial 값을 따로 지정하지 않아도 기본값인 false가 유지되도록 강제하는 어노테이션입니다.
	private boolean fromSocial = false;// from_social
	
	//1.Set: 중복허용 X, 권한 여러개
	//2. @ElementCollection 역할: 한 개의 컬럼을 추가 X
	//							 테이블을 생성해서 roleSet 컬럼 여러개...
	
	@ElementCollection(fetch=FetchType.LAZY)
	@Builder.Default		//필드에 지정한 값을 기본값으로 유지하도록 하는 어노테이션
	
	private Set<ClubMemberRole> roleSet = new HashSet<>();
	
	
	//나중에 권한 추가할 때 사용할 메서드
	public void addMemberRole(ClubMemberRole clubMemberRole) {
		this.roleSet.add(clubMemberRole);
	}

}

