package com.ysh.exam.capstone.restTest.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class allRoomInfo{

	
    private int id;
    private String created_at;
    private String room_name;
    private int user_id;
    private int temp;
    private int humidify;
    private int findedust;
    private String ledcolor;
}

