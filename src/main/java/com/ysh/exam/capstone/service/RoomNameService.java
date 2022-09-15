package com.ysh.exam.capstone.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
	//조인된 테이블 에서 방이름을 가지고 오는 것이 아니라 room테이블 내에서 방이름을 검색해서 받아온다(중복될 일이 없다)
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

}
