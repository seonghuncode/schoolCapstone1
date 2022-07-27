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
    private String pm;
    private String temperature;
    private String humadity;
	

}
