package com.ysh.exam.capstone.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    
    private int id;
    private String roomName;
    private String regDate;    
    private int infoId;

    
    private String joinPm;
	private String joinTemperature;
	private String joinHumadity;
	private String joinUpdateDate;
	

}
