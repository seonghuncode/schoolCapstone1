package com.ysh.exam.capstone.restTest.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



//DTO!!

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {

    private String name;
    private int age;
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class login{

    private String loginId;
    private String loginPw;
}




