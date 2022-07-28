package com.ysh.exam.capstone.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MachineHomeController {
	
	@RequestMapping("/machine/home/main")
	public String showMain() {
		//return "/WEB-INF/jsp/usr/home/main.jsp"; //JSP를 사용하기 위해서는 @ResponseBody를 빼고 실제 경로를 적어준다
		//(하지만 application.yml의 prefix에 /WEB-INF/jsp/를 적어 주었기 때문에 생략하고 아래처럼 사용 가능)
		//suffix를 사용한 결과 .jsp생략 가능
		return "/machine/home/main";
	}
	
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/machine/home/main";
		
		//url에서 "/"이러한 주소로 들어오면 자동으로 /usr/home/main으로 이동한다
		//@ResponseBody를뺀다.
	}
	
	
}
