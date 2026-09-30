package org.doit.ik.persistence;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.doit.ik.domain.DeptVO;

@Mapper
public interface DeptMapper {
	List<DeptVO> getDeptList() throws Exception;
 }
