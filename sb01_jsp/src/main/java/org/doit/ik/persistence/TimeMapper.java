package org.doit.ik.persistence;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeMapper {
	String getTime();
}
