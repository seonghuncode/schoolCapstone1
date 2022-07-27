package com.ysh.exam.capstone.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ysh.exam.capstone.vo.Info;

@Mapper
public interface InfoRepository {

	
	@Select("SELECT * FROM info ORDER BY id ASC")
	public List<Info> getRooms();
	

	
}
