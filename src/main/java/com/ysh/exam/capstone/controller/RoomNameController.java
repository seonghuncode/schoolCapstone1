package com.ysh.exam.capstone.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.capstone.service.InfoService;
import com.ysh.exam.capstone.service.RoomNameService;
import com.ysh.exam.capstone.vo.Info;
import com.ysh.exam.capstone.vo.Room;


@Controller
public class RoomNameController {
	
	
	private RoomNameService roomNameService;
	
	public RoomNameController(RoomNameService machineService) {
		this.roomNameService = machineService;
	}
	

	@RequestMapping("/machine/room/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요!!!";
	}
	
	
	
	@RequestMapping("/machine/room/doAdd")
	@ResponseBody
	public String doAdd(String roomname) {
	//방 이름을 추가	
		int id = roomNameService.checkExist(roomname);
		
		if(roomname == null  || roomname.trim().length() == 0) {
			return "추가 할 방 이름을 입력해 주세요";
		}
		
	
		if(id == -1) {
			return roomname + "은 이미 존재 하는 이름 입니다.";
		}
		
		roomNameService.writeRoomName(roomname);
	
		return "%s이(가) 추가 되었습니다".formatted(roomname);
	}
	
	@RequestMapping("/machine/room/getRoom")
	@ResponseBody
	public Object showRoom(String roomname) {
	//입력한 방에 대한 정보를 보여준다
		Room room = roomNameService.getRoom(roomname);
		
		if(roomname == null  || roomname.trim().length() == 0) {
			return "방 이름을 입력해 주세요";
		}
		
		if(room == null) {
			return roomname + "은 존재하지 않는 방 입니다.";
		}
		return room;
	}
	
	@RequestMapping("/machine/room/showRooms")
	public String showRooms(Model model) {
	//전체 roomname에 대한 정보를 보여준다.
		List<Room> rooms = roomNameService.getRooms();
		model.addAttribute("rooms", rooms);
		
		return "/machine/info/list";
	}
	
	@RequestMapping("/machine/room/doDelete")
	@ResponseBody
	public String doDelete(String roomname) {
		
		Room room = roomNameService.getRoom(roomname);
		
		if(roomname == null || roomname.trim().length() == 0) {
			return "삭제할 방 이름을 입력해 주세요";
		}
		if(room == null) {
			return roomname + "은 존재하지 않는 방 입니다.";
		}
		
		roomNameService.doDelete(roomname);
		
		return roomname + "이 삭제 되었습니다";
	}
	
	
	@RequestMapping("/machine/room/doDetail")
	public String doDetail(Model model, String roomname) {
		
		if(roomname == null || roomname.trim().length() == 0) {
			return "자세히 보고 싶은 방 이름을 입력해 주세요";
		}
		
		Room rooms = roomNameService.getRoom(roomname);
		model.addAttribute("rooms", rooms);
		
		return "/machine/info/detail";
	}
	
	//방 이름 하나당 습도,온도,미세먼지의 정보는 1대 다관계에 있다.
	//테이블을 두개로 나눈다(방이름에는 셍성날짜 id, 정보에는 온도, 습도 ,미세먼지)
	
	
	
}
