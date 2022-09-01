package com.ysh.exam.capstone.util;

public class Ut {

	//Ut.empty ==> 비어있는지 확인하는 함수 
	public static boolean empty(Object obj) { //util기능을 범용적으로 만들기 위해 Object로 받는다
		
		if(obj == null) {
			return true;
		}
		
		//obj instanceof String ==> obj의 객체는 String객체이다 라는 의미
		if(obj instanceof String == false) { //String이 아니면 true
			return true;
		}
		
		String str = (String)obj;
		
		return str.trim().length() == 0;
		//좌우 공백을 보고 길이를 재서 0이변 비어 있다고 본다
		
		
		//매개변수로 받은게 null이고, String이 아니며, 문자열이라도 공백과 길이를 0으로 한다
		//null값과 String이 아닌것을 공백과 길이를 0으로 해주는 작업이다.
		
	}

	
	//f는 format의 약자로 사용
	public static String f(String format, Object... args) { 
		//loginId하나 혹은 name, email두개가 들어 올 수 있기 때문에 Object... args를 사용하면 ==> 뒤에 있는것이 n이든 간에 배열에 들어 간다. 
		return String.format(format, args);
	}//이 구조는 나중에 복사 붙여 넣기 해서 사용할 수 았으면 된다
		
	
	
	
	//컨트롤러 에서 @ResponseBody를 빼지 않고 원하는 페이지로 이동 시켜 주는 기능
	public static String jsReplace(String msg, String uri) {
		if (msg == null) {
			msg = "";
		}

		if (uri == null) {
			uri = "";
		}

		return Ut.f("""
				<script>
				const msg = '%s'.trim();
				if ( msg.length > 0 ) {
				    alert(msg);
				}
				location.replace('%s');
				</script>
				""", msg, uri);
	}
	
	
	//위의jsReplace의 경우 회원가입시 입력폼을 잘못 입력하면 모든 정보를 다시 입력해야 하기 때문에 입력한 정보가 input text에 남아 있게 하기 위해서 뒤로가기 기능으로 사용
	public static String test1(String msg) {
		if (msg == null) {
			msg = "";
		}

		

		return Ut.f("""
				<script>
				const msg = '%s'.trim();
				if ( msg.length > 0 ) {
				    alert(msg);
				}
				history.back();
				</script>
				""", msg);
	}
	
	

	
	
}





