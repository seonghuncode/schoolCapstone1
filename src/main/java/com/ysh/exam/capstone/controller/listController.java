package com.ysh.exam.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysh.exam.capstone.dto.PageDTO;
import com.ysh.exam.capstone.dto.PageParam;
import com.ysh.exam.capstone.repository.RoomNameRepository;
import com.ysh.exam.capstone.service.RoomNameService;
import com.ysh.exam.capstone.vo.Room;

@Controller
public class listController{
	
	@Autowired
	private RoomNameRepository roomNameRepository;
	@Autowired
	private RoomNameService roomNameService;
	
	@RequestMapping("/list")
	public String list(PageParam page, Model model, String roomName) {
		
		int total =  roomNameRepository.getTotalCount("거실", page);
		
		PageDTO pageDTO = new PageDTO(page, total);
		
//		model.addAttribute("boardList", roomNameRepository.getListPaging(page));
		model.addAttribute("page", pageDTO);
		
//		List<Room> room = roomNameService.getRoomInfoTest("거실");
//		model.addAttribute("room", room);
		
		List<Room> room = roomNameRepository.getRoomInfoTest("거실",page.getStart(), page.getAmount());
		model.addAttribute("room", room);

		return "/machine/info/testdetail";
	}
}
