package com.ysh.exam.capstone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ysh.exam.capstone.repository.RoomNameRepository;
import com.ysh.exam.capstone.vo.Info;
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



	public int checkExist(String roomname) {
		
		Room oldrName = getRoom(roomname);
		
		if(oldrName != null) {
			return -1;
		}
		
		return roomNameRepository.getLastInsertId();
		
		
	}



	

	






}
