package com.ysh.exam.capstone.restTest.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Modify {
	 private String result;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class modifyInfo {

    private String old_room_name;
    private String new_room_name;
}

