package com.ysh.exam.capstone.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ysh.exam.capstone.dto.PageDTO;
import com.ysh.exam.capstone.dto.PageParam;
import com.ysh.exam.capstone.restTest.Client.Modify;
import com.ysh.exam.capstone.restTest.Client.RestTemplateService;
import com.ysh.exam.capstone.restTest.Client.Result;
import com.ysh.exam.capstone.restTest.Client.allRoomInfo;
import com.ysh.exam.capstone.restTest.Client.userInfo;
import com.ysh.exam.capstone.service.RoomNameService;
import com.ysh.exam.capstone.util.Ut;
import com.ysh.exam.capstone.vo.Room;
import com.ysh.exam.capstone.vo.UserNowId;


@Controller
public class RoomNameController {
	
//	UserNowId userNowId;
//	 
////    @Autowired(required = false)
////    public void RoomNameController(UserNowId userNowId) {
////        this.userNowId = userNowId;
////    }
	
	



	@Autowired
	private RestTemplateService restTemplateService;

	private RoomNameService roomNameService;

	public RoomNameController(RoomNameService machineService) {
		this.roomNameService = machineService;
	}

	@Autowired
	private UsrMemberController usrMemberController;

	@RequestMapping("/machine/room/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요!!!";
	}

	@RequestMapping("/machine/room/information")
	public String add(Model model, HttpSession httpSession) {
		// --------------------------------------------------------------------------------
		// 현재 존재 하는 방 이름들을 보여주기 위해 넘겨 준다
//		List<Room> rooms = roomNameService.getRooms();
//		model.addAttribute("rooms", rooms);
		// ==>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>위의 내 서버 에서 다영님 서버에 저장된 방들을
		// 불러오는 것으로 수정 하기
		allRoomInfo[] rooms = restTemplateService.allRoomInfo();
		model.addAttribute("rooms", rooms);
		
		
		// --------------------------------------------------------------------------------

		//>>>>>>>>>다영님 서버로 수정(디비에 있는 유저 정보를 불러와서 jsp파일로 보내준다)--------------
//		// 유저 정보를 jsp파일로 보내준다.
//		Member user = (Member) usrMemberController.getUserInformation(httpSession, model);
//		model.addAttribute("user", user);
		
		// ==>  usrMemberController에서 로그인을 하면 전역변수로 loginId들 저장해 두기 때문에 현재 세션에 저장되어 있는 아이디를 리턴받아 가지고 온다.
		String findLoginId = usrMemberController.userNowId.getLoginId();
		String loginId = findLoginId;
		if(loginId == null) {
			return Ut.jsReplace("현재 로그인 아이디를 불러 올수 없습니다.(RoomNameController -> information부분을 확인해 주세요).", "/");
		}
	
		
//		loginId = userNowId.getLoginId();
		System.out.println("============================");
		System.out.println("============================");
		System.out.println(loginId);
		System.out.println("============================");
		System.out.println("============================");
		userInfo[] user = restTemplateService.showUserInfo(loginId);
		
		userInfo realUser = user[0];  //배열에 넣어서 오기 때문에 배열안에 있는 객체로 jsp로 보내주어야 사용 가능 하다.
		model.addAttribute("user", realUser);
		System.out.println(user);
		//>>>>>>>>>다영님 서버로 수정(디비에 있는 유저 정보를 불러와서 jsp파일로 보내준다)--------------
		
		
		// 현재 세션으로 로그인 여부를 판단한다
		boolean isLogined = false;
		if (httpSession.getAttribute("loginedMemberId") == null) {
			isLogined = true;
		}
		if (isLogined) {
			return Ut.jsReplace("현재 로그아웃 되어 있습니다. 가능을 사용하기 위해 먼저 로그인을 해주세요.", "/");
		}

		return "/machine/info/information";
	}

	// 현재 사용 하지 않는 기능
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
		// ----------------------------------------------------------다영님 서버연결 해서 수정
		// 전체 roomname에 대한 정보를 보여준다.
		// List<Room> rooms = roomNameService.getRooms();
		// ==>
		allRoomInfo[] rooms = restTemplateService.allRoomInfo();
		model.addAttribute("rooms", rooms);

		// ----------------------------------------------------------다영님 서버연결 해서 수정

		return "/machine/info/list";
	}

	// machine/room/showRooms에서 동일 방에 대한 다수의 데이터를 평균치로 보여주는 기능으로 수정

	@RequestMapping("/machine/room/doDelete")
	@ResponseBody
	public String doDelete(String roomname) {

		// 다영님 서버랑 연결 하여 삭제 해당 방 삭제 하는 기능으로 수정-------------------------------------------
//		int id = roomNameService.checkExist(roomname);
//		if (roomname == null || roomname.trim().length() == 0) {
//			return Ut.jsReplace(Ut.f("삭제할 방 이름을 선택해 주세요"), "/machine/room/showRooms");
//		}
//		if (id != -1) {
//			return Ut.jsReplace(Ut.f("%s방은 존재 하지 않는 방 입니다.", roomname), "/machine/room/showRooms");
//		}
//		roomNameService.doDelete(roomname);
		// 다영님 서버랑 연결 하여 삭제 해당 방 삭제 하는 기능으로 수정-------------------------------------------

//		return roomname + "이 삭제 되었습니다";

		if (roomname == null || roomname.trim().length() == 0) {
			return Ut.jsReplace(Ut.f("삭제할 방 이름을 선택해 주세요"), "/machine/room/showRooms");
		}
		
		Result result = restTemplateService.deleteRoomData(roomname);
		if(result.getResult().equals("FALSE")) {
			return Ut.jsReplace(Ut.f("%s방에 데이터가 존재 하지 않습니다.", roomname), "/machine/room/showRooms");
		}
		
		System.out.println("===============================================");
		System.out.println("===============================================");
		System.out.println("===============================================");
		System.out.println(result.getResult());
		//다영님 서버 에서 삭제 요청을 하면 최근 순으로 여러개의 방 데이터 에서 나중것 부터 삭제 하기 때문에
		//==> 요청을 보내서 false가 리턴될때 까지 서버애 요청을 하여 해당 방에 대한 모든 데이터를 삭제 하도록 한다.
		Result value = result;
		int cnt = 1;
		while(true) {
			value = restTemplateService.deleteRoomData(roomname);
			cnt++;
			if(value.getResult().equals("FALSE")) {
				break;
			}
			System.out.println("==================================");
			System.out.println("==================================");
			System.out.println("==================================");
			System.out.println(cnt);
		}
		

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

		//----------------------------------------------------------다영님 서버랑 연결한 것으로 수정하기
		//int total = roomNameService.getTotalCount(roomName, page);  //현재 서버에서 카운트 해주는 기능이 없기 때문에 방이름으로 해당 전체 방을 불러와서 직접 카운트 하기
		//===>
		allRoomInfo[] rooms = restTemplateService.findRoom(roomName);
		int cnt = 0;
		for(int i = 0; i < rooms.length; i++) {
			cnt++;
		}
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println(cnt);
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		int total = cnt;
		//----------------------------------------------------------다영님 서버랑 연결한 것으로 수정하기

		PageDTO pageDTO = new PageDTO(page, total);
		model.addAttribute("page", pageDTO);
		

//		List<Room> room = roomNameService.getRoomInfo(roomName);
//		model.addAttribute("room", room);

		// 만약 검색 기능에서 searchRegdate를 보내 값이 들어 있다면 이값으로 전달 해라
		// 검색 기능에서 객체를 전달할 수 없기 때문에 searchText만 보내서 여기서 계산한다
//		if (searchRegDate != null) {
		if (searchText != null && searchField.equals("regDate")) {
			//다영님 서버로 수정 하기--------------------------------------------------------------------------------------
//			List<Room> searchRegdate = roomNameService.findRegDate(searchText, roomName, page.getStart(),
//					page.getAmount());
			// ==>
			allRoomInfo[] searchRegdate = restTemplateService.showSearchReDate(searchText, roomName, page.getStart() + 1,
					page.getAmount() + 1);
			
			
		
			
			//다영님 서버로 수정 하기--------------------------------------------------------------------------------------
			
			model.addAttribute("room", searchRegdate);
			
			if (searchRegdate == null) {
				// 값이 없을 경우 jsp의 오류 페이지를 띄우고 다시 detail화면으로 해당 값을 가지고 보내주기 위해서
				String msg = "[" + searchText + "] 가 포함되는 정보는 존재 하지 않습니다.";
				model.addAttribute("msg", msg);
				return "/machine/info/emptysearch";
			}
			return "/machine/info/detail";
		} else if (searchText != null && searchField.equals("joinPm")) {
			//다영님 서버랑 연결 하기-----------------------------------------------------------------------------
//			List<Room> searchjoinPm = roomNameService.findjoinPm(searchText, roomName, page.getStart(),
//					page.getAmount());
			// ==> 
			
			allRoomInfo[] searchjoinPm = restTemplateService.showSearchFindDust(searchText, roomName, page.getStart() + 1,
					page.getAmount() + 1);
			
			//다영님 서버랑 연결 하기-----------------------------------------------------------------------------
			model.addAttribute("room", searchjoinPm);
			if (searchjoinPm == null) {
				// 값이 없을 경우 jsp의 오류 페이지를 띄우고 다시 detail화면으로 해당 값을 가지고 보내주기 위해서
				String msg = "[" + searchText + "] 가 포함되는 정보는 존재 하지 않습니다.";
				model.addAttribute("msg", msg);
				return "/machine/info/emptysearch";
			}
			return "/machine/info/detail";
		} else if (searchText != null && searchField.equals("joinTemperature")) {
			//다영님 서버랑 연결 하기-------------------------------------------------------------------------------------
//			List<Room> searchjoinTemperature = roomNameService.findjoinTemperature(searchText, roomName,
//					page.getStart(), page.getAmount());
			
			allRoomInfo[] searchjoinTemperature = restTemplateService.showSearchFindTemp(searchText, roomName,
					page.getStart() + 1, page.getAmount() + 1);
			
			//다영님 서버랑 연결 하기-------------------------------------------------------------------------------------
			
			model.addAttribute("room", searchjoinTemperature);
			if (searchjoinTemperature == null) {
				// 값이 없을 경우 jsp의 오류 페이지를 띄우고 다시 detail화면으로 해당 값을 가지고 보내주기 위해서
				String msg = "[" + searchText + "] 가 포함되는 정보는 존재 하지 않습니다.";
				model.addAttribute("msg", msg);
				return "/machine/info/emptysearch";
			}
			return "/machine/info/detail";
		} else if (searchText != null && searchField.equals("joinHumidity")) {
			//다영님 서버랑 연결 하기-----------------------------------------------------------------------------------------
//			List<Room> searchjoinHumidity = roomNameService.findjoinHumidity(searchText, roomName, page.getStart(),
//					page.getAmount());
			
			allRoomInfo[] searchjoinHumidity = restTemplateService.showSearchFindHumidity(searchText, roomName, page.getStart() + 1,
					page.getAmount() + 1);
			
			//다영님 서버랑 연결 하기-----------------------------------------------------------------------------------------
			model.addAttribute("room", searchjoinHumidity);
			if (searchjoinHumidity == null) {
				// 값이 없을 경우 jsp의 오류 페이지를 띄우고 다시 detail화면으로 해당 값을 가지고 보내주기 위해서
				String msg = "[" + searchText + "] 가 포함되는 정보는 존재 하지 않습니다.";
				model.addAttribute("msg", msg);
				return "/machine/info/emptysearch";
			}
			return "/machine/info/detail";
			// 원래 페이지 기능 으로 기본적으로 선택이 안되어 있을 경우 전체 데이터를 불러오는 코드
		} else {
			//----------------------------------------------------------다영님 서버랑 연결한 것으로 수정하기
			
//			List<Room> room = roomNameService.getRoomInfoPaging(roomName, page.getStart(), page.getAmount());
//			model.addAttribute("room", room);
			//==>
			//서버에 시작이 1부터 인데 start , amount ==> 0 ~ 6이기 때문에 +1 씩해서 서버로 보내준다.
			allRoomInfo[] room = restTemplateService.showDetailData(roomName, page.getStart()+ 1, page.getAmount() + 1);
			model.addAttribute("room", room);

			return "/machine/info/detail";
			
			//----------------------------------------------------------다영님 서버랑 연결한 것으로 수정하기
		}
	}

	// 수정 하기 기능
	@RequestMapping("/machine/room/modify")
	public String Modify(Model model, String roomname) {

		// 다영님 서버로 수정
		// 하기-----------------------------------------------------------------------------------------
//		Room room = roomNameService.getSameRooms(roomname);
		// ==>
		allRoomInfo[] room = restTemplateService.findRoom(roomname);
		model.addAttribute("room", room);

		// List<Room> rooms = roomNameService.getRooms(); // 하단 현존하는 방 이름을 보여주기 위한 기능
		// ==>
		allRoomInfo[] rooms = restTemplateService.allRoomInfo();
		model.addAttribute("rooms", rooms);

		String nowRoomName = roomname;
		model.addAttribute("nowRoomName", nowRoomName);
		// 다영님 서버로 수정
		// 하기-----------------------------------------------------------------------------------------

		return "/machine/info/modify";

	}

	@RequestMapping("/machine/room/doModify")
	@ResponseBody
	public Object doModify(String roomnameOld, String roomnameNew) {
		// --------------------------------------------------------------다영님 서버랑 연결하는
		// 코드로 변경 하기
//		int id = roomNameService.checkExist(roomnameNew);
//		if (id == -1) {
//			return Ut.test1("이미 존재 하는 방 이름 입니다.");
//		}
//		Room room = roomNameService.getSameRooms(roomnameOld);
//		int roomId = room.getId();
//		roomNameService.doModify(roomId, roomnameNew);
//		return Ut.jsReplace(Ut.f("%s이(가)  %s로 수정 되었습니다.", roomnameOld, roomnameNew), "/machine/room/information");

		Modify result = restTemplateService.doModify(roomnameOld, roomnameNew);
		if (result.getResult().equals("FALSE")) {
			return Ut.test1("이미 존재 하는 방 이름 입니다.");
		}

		return Ut.jsReplace(Ut.f("%s이(가)  %s로 수정 되었습니다.", roomnameOld, roomnameNew), "/machine/room/information");

		// --------------------------------------------------------------다영님 서버랑 연결하는
		// 코드로 변경 하기
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

	@RequestMapping("/show/graph")
	public String show(Model model) { // 전체 roomname에 대한 정보를 보여준다.
		List<Room> rooms = roomNameService.getRooms();

		model.addAttribute("rooms", rooms);
		model.addAttribute("test", "test");
		return "/machine/test/graph";
	}

	@RequestMapping("/machine/room/showGraph")
	public String showGraph(Model model, String roomname) {
		//다영님 서버랑 연결해서 통계자료 보여주는 기능 으로 수정 하기---------------------------
//		List<Room> rooms = roomNameService.getRooms();
//		model.addAttribute("rooms", rooms); // 전체 방에서 현재 방 데이터를 뽑아 통계할 예정
//		model.addAttribute("nowRoomName", roomname); // 현재 방에 대한 이름을 넘겨 준다
		
		// ===>>
		allRoomInfo[] rooms = restTemplateService.findRoom(roomname);
		model.addAttribute("rooms", rooms);
		model.addAttribute("nowRoomName", roomname);
		
		//다영님 서버랑 연결해서 통계자료 보여주는 기능 으로 수정 하기---------------------------

		return "/machine/info/showgraph";
	}

}
