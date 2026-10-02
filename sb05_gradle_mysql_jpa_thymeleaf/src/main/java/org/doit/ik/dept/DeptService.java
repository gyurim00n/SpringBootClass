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

	}

	//[1-2] 부서 정보를 조회 서비스 + 페이징 처리
	public Page<Dept> getDeptsByPage(int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		return this.deptRepository.findAll(pageable);
	}

	public Dept saveDept(Dept dept) {
		return this.deptRepository.save(dept);
	}

	public Dept updateDept(Dept dept) {

		this.deptRepository
		.findById(dept.getDeptno())
		.orElseThrow(()-> new IllegalArgumentException("수정할 부서가 존재하지 x"));

		return this.deptRepository.save(dept);
	}

	public void deleteDept(Integer deptno) {
		this.deptRepository
		.findById(deptno)
		.orElseThrow(()-> new IllegalArgumentException("삭제할 부서가 존재하지 x"));

		this.deptRepository.deleteById(deptno);
		
	}

}
