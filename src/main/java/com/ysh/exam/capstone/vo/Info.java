package com.ysh.exam.capstone.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    
    private int id;
    private String updateDate;    
    private String pm;
    private String temperature;
    private String humadity;
    private int roomId;
    
    private String joinName;
	

}
