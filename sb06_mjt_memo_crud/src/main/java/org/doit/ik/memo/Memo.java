package org.doit.ik.memo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbl_memo")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Memo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mno;	//MySQL, MariaDB	자동 증가. auto increment
	
	//이것에 의해 네이밍됨:
	//spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
	//@Transient	실제 테이블에 컬럼으로 생성되지 않을경우 사용.
	//@Column(columnDefinition = "varchar(255) default 'Yes'")
	@Column(length = 30, nullable = false )
	private String memoText; // DB 에서는 컬럼명 memo_text (기억해두자.)
	

	
}
