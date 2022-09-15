package com.ysh.exam.capstone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.capstone.service.RoomNameService;
import com.ysh.exam.capstone.util.Ut;
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

	@RequestMapping("/machine/room/add")
	public String add(Model model) {
		//현재 존재 하는 방 이름들을 보여주기 위해 넘겨 준다
		List<Room> rooms = roomNameService.getRooms();

		model.addAttribute("rooms", rooms);

		return "/machine/info/addroom";
	}

	@RequestMapping("/machine/room/doAdd")
	@ResponseBody
	public String doAdd(String roomname) {
		// 방 이름을 추가
		int id = roomNameService.checkExist(roomname);

		if (roomname == null || roomname.trim().length() == 0) {
//			return "추가 할 방 이름을 입력해 주세요";
			return Ut.jsReplace(Ut.f("추가할 방 이름을 입력해 주세요"), "/machine/room/add");
		}

		if (id == -1) {
//			return roomname + "은 이미 존재 하는 이름 입니다.";
			return Ut.jsReplace(Ut.f("%s는 이미 존재 하는 방 이름 입니다.", roomname), "/machine/room/add");
		}

		roomNameService.writeRoomName(roomname);

//		return "%s이(가) 추가 되었습니다".formatted(roomname);
		return Ut.jsReplace(Ut.f("%s방이 추가 되었습니다.", roomname), "/");
	}

	@RequestMapping("/machine/room/getRoom")
	@ResponseBody
	public Object showRoom(String roomname) {
		// 입력한 방에 대한 정보를 보여준다
		Room room = roomNameService.getRoom(roomname);

		if (roomname == null || roomname.trim().length() == 0) {
			return "방 이름을 입력해 주세요";
		}

		if (room == null) {
			return roomname + "은 존재하지 않는 방 입니다.";
		}
		return room;
	}

	@RequestMapping("/machine/room/showRooms")
	public String showRooms(Model model) {
		// 전체 roomname에 대한 정보를 보여준다.
		List<Room> rooms = roomNameService.getRooms();

		model.addAttribute("rooms", rooms);

		return "/machine/info/list";
	}

	// machine/room/showRooms에서 동일 방에 대한 다수의 데이터를 평균치로 보여주는 기능으로 수정

	@RequestMapping("/machine/room/doDelete")
	@ResponseBody
	public String doDelete(String roomname) {

		int id = roomNameService.checkExist(roomname);

		if (roomname == null || roomname.trim().length() == 0) {
//			return "삭제할 방 이름을 입력해 주세요";
			return Ut.jsReplace(Ut.f("삭제할 방 이름을 선택해 주세요"), "/machine/room/showRooms");
		}
		if (id != -1) {
//			return roomname + "은 존재하지 않는 방 입니다.";
			return Ut.jsReplace(Ut.f("%s방은 존재 하지 않는 방 입니다.", roomname), "/machine/room/showRooms");
		}

		roomNameService.doDelete(roomname);

//		return roomname + "이 삭제 되었습니다";
		return Ut.jsReplace(Ut.f("%s방이 삭제 되었습니다.", roomname), "/machine/room/showRooms");
	}

//	@RequestMapping("/machine/room/doDetail")
//	public String doDetail(Model model, String roomName) {
//		
//		if(roomName == null || roomName.trim().length() == 0) {
//			return "자세히 보고 싶은 방 이름을 입력해 주세요";
//		}
//		
//		Room room = roomNameService.getRoom(roomName);
//		model.addAttribute("room", room);
//		
//		return "/machine/info/detail";
//	}

	@RequestMapping("/machine/room/doDetail")
	public String doDetail(Model model, String roomName) {

		if (roomName == null || roomName.trim().length() == 0) {
			return "자세히 보고 싶은 방 이름을 입력해 주세요";
		}

		List<Room> room = roomNameService.getRoomInfo(roomName);
		model.addAttribute("room", room);

		return "/machine/info/detail";
	}
	
	//수정 하기 기능
	@RequestMapping("/machine/room/modify")
	public String Modify(Model model, String roomname) {

		Room room = roomNameService.getSameRooms(roomname);

		model.addAttribute("room", room);
		
		return "/machine/info/modify";
		
	}
	
	
	@RequestMapping("/machine/room/doModify")
	@ResponseBody
	public String doModify(String roomnameOld, String roomnameNew) {
		int id = roomNameService.checkExist(roomnameNew);
		if (id == -1) {
//			return Ut.jsReplace(Ut.f("%s는 이미 존재 하는 방 이름 입니다.", roomnameNew), "/machine/room/modify");
			return Ut.test1("이미 존재 하는 방 이름 입니다.");
		}
		
		Room room = roomNameService.getSameRooms(roomnameOld);
		int roomId = room.getId();
		
		roomNameService.doModify(roomId,  roomnameNew);
		
		return Ut.jsReplace(Ut.f("%s이(가)  %s로 수정 되었습니다.", roomnameOld, roomnameNew), "/machine/room/showRooms");
	}
	
	
	
	

}
