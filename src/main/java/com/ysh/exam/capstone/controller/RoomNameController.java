package com.ysh.exam.capstone.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String doAdd(String roomname, String pm, String temperature, String humadity) {
	//방 이름을 추가	
		int id = roomNameService.checkExist(roomname);
		
		if(roomname == null  || roomname.trim().length() == 0) {
			return "추가 할 방 이름을 입력해 주세요";
		}
		
		if(pm == null  || pm.trim().length() == 0) {
			return "추가 할 미세먼지 수치를 입력해 주세요";
		}
		
		if(temperature == null  || temperature.trim().length() == 0) {
			return "추가 온도를 입력헤 주세요";
		}
		
		if(humadity == null  || humadity.trim().length() == 0) {
			return "추가 습도를 입력해 주세요";
		}
		
		if(id == -1) {
			return roomname + "은 이미 존재 하는 이름 입니다.";
		}
		
		roomNameService.writeRoomName(roomname, pm, temperature, humadity);
	
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
	@ResponseBody
	public List<Room> showRooms() {
	//전체 roomname에 대한 정보를 보여준다.
		return roomNameService.getRooms();
	
	}
	
	
	
}
