package com.ysh.exam.capstone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ysh.exam.capstone.repository.InfoRepository;
import com.ysh.exam.capstone.vo.Info;
import com.ysh.exam.capstone.vo.Room;

@Service
public class InfoService {
	
	
	
	private InfoRepository infoRepository;
	
	public InfoService(InfoRepository infoRepository) {
		this.infoRepository = infoRepository;
	}
	

	public List<Info> showAllinfo() {
		return infoRepository.showAllinfo();
	}


	

	






}
