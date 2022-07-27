package com.ysh.exam.capstone.controller;




import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.capstone.service.RoomNameService;
import com.ysh.exam.capstone.service.InfoService;
import com.ysh.exam.capstone.vo.Info;
import com.ysh.exam.capstone.vo.Room;


@Controller
public class InfoController {
	
	
	private InfoService infoService;
	
	public InfoController(InfoService infoService) {
		this.infoService = infoService;
	}
	
	
	
	
	@RequestMapping("/machine/info/showInfo")
	@ResponseBody
	public List<Info> showInfo() {
	//전체 roomname에 대한 정보를 보여준다.
		return infoService.getRooms();
	
	}
	
	
}
