package org.doit.ik.dept;

import java.util.List;

import org.doit.ik.emp.Emp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbl_dept")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dept {
	
	@Id
	@SequenceGenerator(name="seq_deptno", sequenceName = "seq_tbldept_deptno", initialValue= 10, allocationSize = 10)
	@GeneratedValue(generator = "seq_deptno", strategy = GenerationType.SEQUENCE)
	private Integer deptno;
	
	@Column(length=14, nullable = true)
	private String dname;
	
	@Column(length=13, nullable = true)
	private String loc;
	
	// 생성자 2 추가 설정
	public Dept(String dname, String loc) {
			this.dname = dname;
			this.loc = loc;
		}
	
	//1:N
	//	mappedBy = "dept" 연관관계의 주인은 다른 엔티티의 dept 필드이다.
	//	Emp 엔티티의 private Dept, dept; 필드명
	//fetch eager: 정보 바로 가져오기 
	// cascade = CascadeType.ALL : 부모엔티티에 수행한 영속성 관련 작업을 자식 엔티티에도 모두 전파한다. 
	// 예) 부서의 40번 삭제 -> 사원 40번 사원 모두 삭제...
	@OneToMany(mappedBy = "dept", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Emp> empList;
	
}
