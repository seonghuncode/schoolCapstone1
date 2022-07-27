package com.ysh.exam.capstone.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {

	 private int id;
	 private String regDate;
	 private int pm;
	 private int temperature;
	 private int humadity;
	 private int roomId;
	

}
