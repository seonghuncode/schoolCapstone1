package com.ysh.exam.capstone.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ysh.exam.capstone.dto.PageDTO;
import com.ysh.exam.capstone.dto.PageParam;
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
		// 현재 존재 하는 방 이름들을 보여주기 위해 넘겨 준다
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

//List를 매개변수로 받을 경우 오류가 생기기 때문에 RequestParam을 적어준다, 값이 없을 경우 오류가 발생하기 때문에 기본값을 false로 해준다
//	@RequestParam(value = "searchRegDate", required = false) List<Room> searchRegDate
	@RequestMapping("/machine/room/doDetail")
	public Object doDetail(Model model, String roomName, PageParam page, String searchText, String searchField) {

		if (roomName == null || roomName.trim().length() == 0) {
			return "자세히 보고 싶은 방 이름을 입력해 주세요";
		}

		int total = roomNameService.getTotalCount(roomName, page);

		PageDTO pageDTO = new PageDTO(page, total);
		model.addAttribute("page", pageDTO);

//		List<Room> room = roomNameService.getRoomInfo(roomName);
//		model.addAttribute("room", room);

		// 만약 검색 기능에서 searchRegdate를 보내 값이 들어 있다면 이값으로 전달 해라
		// 검색 기능에서 객체를 전달할 수 없기 때문에 searchText만 보내서 여기서 계산한다
//		if (searchRegDate != null) {
		if (searchText != null && searchField.equals("regDate")) {
			List<Room> searchRegdate = roomNameService.findRegDate(searchText, roomName, page.getStart(),
					page.getAmount());
			model.addAttribute("room", searchRegdate);
			if (searchRegdate.isEmpty()) {
				// 값이 없을 경우 jsp의 오류 페이지를 띄우고 다시 detail화면으로 해당 값을 가지고 보내주기 위해서
				String msg = "[" + searchText +  "] 가 포함되는 정보는 존재 하지 않습니다.";
				model.addAttribute("msg", msg);
				return "/machine/info/emptysearch";
			}
			return "/machine/info/detail";
		} else if (searchText != null && searchField.equals("joinPm")) {
			List<Room> searchjoinPm = roomNameService.findjoinPm(searchText, roomName, page.getStart(),
					page.getAmount());
			model.addAttribute("room", searchjoinPm);
			if (searchjoinPm.isEmpty()) {
				// 값이 없을 경우 jsp의 오류 페이지를 띄우고 다시 detail화면으로 해당 값을 가지고 보내주기 위해서
				String msg = "[" + searchText +  "] 가 포함되는 정보는 존재 하지 않습니다.";
				model.addAttribute("msg", msg);
				return "/machine/info/emptysearch";
			}
			return "/machine/info/detail";
		} else if (searchText != null && searchField.equals("joinTemperature")) {
			List<Room> searchjoinTemperature = roomNameService.findjoinTemperature(searchText, roomName,
					page.getStart(), page.getAmount());
			model.addAttribute("room", searchjoinTemperature);
			if (searchjoinTemperature.isEmpty()) {
				// 값이 없을 경우 jsp의 오류 페이지를 띄우고 다시 detail화면으로 해당 값을 가지고 보내주기 위해서
				String msg = "[" + searchText +  "] 가 포함되는 정보는 존재 하지 않습니다.";
				model.addAttribute("msg", msg);
				return "/machine/info/emptysearch";
			}
			return "/machine/info/detail";
		} else if (searchText != null && searchField.equals("joinHumidity")) {
			List<Room> searchjoinHumidity = roomNameService.findjoinHumidity(searchText, roomName, page.getStart(),
					page.getAmount());
			model.addAttribute("room", searchjoinHumidity);
			if (searchjoinHumidity.isEmpty()) {
				// 값이 없을 경우 jsp의 오류 페이지를 띄우고 다시 detail화면으로 해당 값을 가지고 보내주기 위해서
				String msg = "[" + searchText +  "] 가 포함되는 정보는 존재 하지 않습니다.";
				model.addAttribute("msg", msg);
				return "/machine/info/emptysearch";
			}
			return "/machine/info/detail";
			//원래 페이지 기능 으로 기본적으로 선택이 안되어 있을 경우 전체 데이터를 불러오는 코드
		} else {
			List<Room> room = roomNameService.getRoomInfoPaging(roomName, page.getStart(), page.getAmount());
			model.addAttribute("room", room);

			return "/machine/info/detail";
		}
	}

	// 수정 하기 기능
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

		roomNameService.doModify(roomId, roomnameNew);

		return Ut.jsReplace(Ut.f("%s이(가)  %s로 수정 되었습니다.", roomnameOld, roomnameNew), "/machine/room/showRooms");
	}

	// 검색 기능 만들기
	@RequestMapping("machine/room/searchList")
	public Object searchList(String searchField, String searchText, String roomName, PageParam page, Model model,
			RedirectAttributes redirect) {
		// 검색에서 전체를 클릭 하게 되면 redirect에 roomName을 넣어서 room/doDetail url로 보내 해당 방에 대한 전체
		// 정보를 보여준다
		if (searchField.equals("0")) {
			redirect.addAttribute("roomName", roomName);
			return "redirect:/machine/room/doDetail";
			// 만약 searchFiel가 regDate라면 redirect에 searchText를 보내 거기서 해당 정보만 받아 jsp로 보내준다.
		} else if (searchField.equals("regDate") && !searchText.equals("")) {
			redirect.addAttribute("roomName", roomName);
			redirect.addAttribute("searchText", searchText);
			redirect.addAttribute("searchField", searchField);
			return "redirect:/machine/room/doDetail";
		}
		// 미세먼지를 검색했을 경우 해당 값의 미세먼지만 보여준다
		else if (searchField.equals("joinPm") && !searchText.equals("")) {
			redirect.addAttribute("roomName", roomName);
			redirect.addAttribute("searchText", searchText);
			redirect.addAttribute("searchField", searchField);
			return "redirect:/machine/room/doDetail";
		} else if (searchField.equals("joinTemperature") && !searchText.equals("")) {
			redirect.addAttribute("roomName", roomName);
			redirect.addAttribute("searchText", searchText);
			redirect.addAttribute("searchField", searchField);
			return "redirect:/machine/room/doDetail";
		} else if (searchField.equals("joinHumidity") && !searchText.equals("")) {
			redirect.addAttribute("roomName", roomName);
			redirect.addAttribute("searchText", searchText);
			redirect.addAttribute("searchField", searchField);
			return "redirect:/machine/room/doDetail";
		}

		return "여기 까지 오는 오류 발생시 return 수정(jsp로 보내는 return만 가능)";
	}

}
