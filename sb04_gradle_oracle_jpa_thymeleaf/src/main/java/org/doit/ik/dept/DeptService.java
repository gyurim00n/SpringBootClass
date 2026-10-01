package org.doit.ik.dept;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {

	private final DeptRepository deptRepository;
	//[1] 부서 정보를 조회 서비스
	public List<Dept> getDepts(){
		return	this.deptRepository.findAll();
		/*	
		 		select
		         	d1_0.deptno,
        			d1_0.dname,
        			d1_0.loc 
				    from
				        tbl_dept d1_0
		 */

	}

	//[1-2] 부서 정보를 조회 서비스 + 페이징 처리
	public Page<Dept> getDeptsByPage(int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		return this.deptRepository.findAll(pageable);
		//		    offset
		//	        ? rows 
		//	    fetch
		//	        first ? rows only
	}

	public Dept saveDept(Dept dept) {
		return this.deptRepository.save(dept);

	}

	public Dept updateDept(Dept dept) {
		/*
			// 1. 수정할 deptno 에 해당하는 부서가 존재하지 X일 경우 예외 발생
			Dept findDept = this.deptRepository.getReferenceById(dept.getDeptno());
			if(findDept == null) {
				throw //강제 예외 발생
			}
		 */

		/*
			Optional<Dept> odept = this.deptRepository.findById(dept.getDeptno());
			odept.orElseThrow(()-> new IllegalArgumentException("수정할 부서가 존재하지 x"));
		 */
		this.deptRepository
		.findById(dept.getDeptno())
		.orElseThrow(()-> new IllegalArgumentException("수정할 부서가 존재하지 x"));

		// 2. 수정 처리 : 수정된 Dept 객체를 반환.



		return this.deptRepository.save(dept);
	}

	public void deleteDept(Integer deptno) {	//수업에서는 deptno를 변수로 받음 
		this.deptRepository
		.findById(deptno)
		.orElseThrow(()-> new IllegalArgumentException("삭제할 부서가 존재하지 x"));

		this.deptRepository.deleteById(deptno);
		
	}

}
