package com.ysh.exam.capstone.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysh.exam.capstone.service.InfoService;
import com.ysh.exam.capstone.vo.Info;
import com.ysh.exam.capstone.vo.Room;


@Controller
public class InfoController {
	
	
	private InfoService infoService;
	
	public InfoController(InfoService infoService) {
		this.infoService = infoService;
	}
	

	
	
	@RequestMapping("/machine/info/showAllInfo")
	public String showAllinfo(Model model) {
	//전체 roomname에 대한 정보를 보여준다.
		List<Info> AllInfo = infoService.showAllinfo();
		model.addAttribute("AllInfo", AllInfo);
		
		return "/machine/info/list";
	}
	
	
	

	

	
	
	
}
