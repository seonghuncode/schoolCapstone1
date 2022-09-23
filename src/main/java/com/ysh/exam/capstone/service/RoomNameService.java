package com.ysh.exam.capstone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ysh.exam.capstone.dto.PageParam;
import com.ysh.exam.capstone.repository.RoomNameRepository;
import com.ysh.exam.capstone.vo.Room;



@Service
public class RoomNameService {

	private RoomNameRepository roomNameRepository;

	public RoomNameService(RoomNameRepository machineRepository) {
		this.roomNameRepository = machineRepository;
	}

	public int writeRoomName(String roomname) {
		roomNameRepository.writeRoomName(roomname);
		return roomNameRepository.getLastInsertId();
	}

	public Room getRoom(String roomname) {

		return roomNameRepository.getRoom(roomname);
	}

	public List<Room> getRoomInfo(String roomname) {

		return roomNameRepository.getRoomInfo(roomname);
	}

	public int checkExist(String roomname) {

		Room oldName = getSameRooms(roomname);

		if (oldName != null) {
			return -1;
		}
		return roomNameRepository.getLastInsertId();
	}

	// 조인된 테이블 에서 방이름을 가지고 오는 것이 아니라 room테이블 내에서 방이름을 검색해서 받아온다(중복될 일이 없다)
	public Room getSameRooms(String roomname) {
		return roomNameRepository.getSameRooms(roomname);
	}

	public List<Room> getRooms() {
		return roomNameRepository.getRooms();
	}

	public void doDelete(String roomname) {
		roomNameRepository.doDelete(roomname);
	}

	public void doModify(int roomId, String roomnameNew) {
		roomNameRepository.doModify(roomId, roomnameNew);

	}

	public int getTotalCount(String roomName, PageParam page) {
		return roomNameRepository.getTotalCount(roomName, page);
	}

	public List<Room> getRoomInfoPaging(String roomName, int start, int amount) {
		return roomNameRepository.getRoomInfoPaging(roomName, start, amount);
	}

	//검색 기능에서 날짜에 대한 해당 검색어만  찾아오는 기능
	public List<Room> findRegDate(String searchText, String roomName, int start, int amount) {
		return roomNameRepository.findeRegDate(searchText, roomName, start, amount);
		
	}
	//검색 기능 에서 미세먼지에 대한 검색 값만 가지고 오는 코드
	public List<Room> findjoinPm(String searchText, String roomName, int start, int amount) {
		return roomNameRepository.findjoinPm(searchText, roomName, start, amount);
	}
	//검색 기능에서 온도를 검색 했을때 해당 값에 속한 값만 불러온다
	public List<Room> findjoinTemperature(String searchText, String roomName, int start, int amount) {
		return roomNameRepository.findejoinTemperature(searchText, roomName, start, amount);
	}

	public List<Room> findjoinHumidity(String searchText, String roomName, int start, int amount) {
		return roomNameRepository.findjoinHumidity(searchText, roomName, start, amount);
	}


	






}
