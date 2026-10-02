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
@Table(name="dept")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToMany(mappedBy = "dept", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Emp> empList;
	
}
