package org.doit.ik.emp;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.doit.ik.dept.Dept;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="emp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
	
	@Id
	@Column(name="empno", nullable=false)
	private Integer empno;
	
	@Column(name="ename", length=10)
	private String ename;
	
	@Column(name="job", length=9)
	private String job;
	
	@Column(name="mgr")
	private Integer mgr;
	
	@Column(name="hiredate")
	private LocalDate hiredate;
	
	@Column(name="sal", precision=7, scale=2)
	private BigDecimal sal;
	
	@Column(name="comm", precision=7, scale=2)
	private BigDecimal comm;

	@ManyToOne
	@JoinColumn(name="deptno")
	private Dept dept;		//dept_deptno ÄÃ·³¸í
}
