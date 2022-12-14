package com.ysh.exam.capstone.restTest.Client;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ysh.exam.capstone.restTest.ignoreHttps;

import lombok.extern.slf4j.Slf4j;

//Service!!!

@Slf4j
@Service
public class RestTemplateService {

	// The method init(KeyManager[], TrustManager[], SecureRandom) ==> https로 요청을 할
	// 경우 key값을 설정해 주는 설정을 해주어야 한다.
	// 원인은 사설 인증서의 경우에 신뢰하는 인증 기관 목록(keystore)에 없어서 발생한다.
	// ------------------------------------------------------------------------------
	// https오류를 무시 하기 위한 코드 - 원래는 보안상 인증키를 추가해주는 것이 좋다
	private ignoreHttps ignoreHttps;

	// http://localhost/api/server/hello
	// response
	public UserResponse hello() {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8081").path("/api/server/hello")
				.queryParam("name", "steve").queryParam("age", 13).encode().build().toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!
		ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();
	}

//	// 준석 서버로 get방식으로 로그인 요청 203.250.133.144:8080/userJoin / string 으로 "true" ,
//	// "false"로 반환
//	// api.add_resource(userJoin, "/userJoin/<'string:id'>/<'string:pw'>")
//	// request객체에 body붙여서 보내면 response를 보내준다
//	public userJoin login() { // userJoin이라는 리턴 받을 class를 만들어 주어야 된다
//		URI uri = UriComponentsBuilder.fromUriString("http://203.250.133.144:8080").path("/userJoin/{id}/{pw}")
////				.queryParam("id", "steve")
////				.queryParam("pw", "df")
//				.encode().build().expand("123", "123").toUri();
//		System.out.println(uri.toString());
//
//		login req = new login();
//		req.setLoginId("admin");
//		req.setLoginPw("admin");
//
//		RestTemplate restTemplate = new RestTemplate();
////        String result = restTemplate.getForObject(uri, String.class);
//		// json 형태로 받자!
//		ResponseEntity<userJoin> result = restTemplate.getForEntity(uri, userJoin.class);
////        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);
//
//		System.out.println(result.getStatusCode());
//		System.out.println(result.getBody());
//
//		return result.getBody();
//	}

	// post 구현
	public UserResponse post() {
		// http://localhost:9090/api/server/user/{userId}/name/{userName}

		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8081")
				.path("/api/server/user/{userId}/name/{userName}").encode().build().expand(11, "steve") // uri
																										// :http://localhost:8081/api/server/user/11/name/steve
				.toUri();

		System.out.println("uri :" + uri);

		// http body -> object -> object mapper -> json -> rest template -> http body
		// json
		UserRequest req = new UserRequest();
		req.setName("dsg");
		req.setAge(10);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);

		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());

		return response.getBody();
	}

//	public Object test() {
//		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8081")
//				.path("/api/server/machine/member/doLogin?loginId={loginId}&loginPw={loginPw}").encode().build()
//				.expand("admin", "admin") // 파라미터로 넘겨줄 값
//				.toUri();
//
//		System.out.println("uri :" + uri);
//
//		// http body -> object -> object mapper -> json -> rest template -> http body
//		// json
////         UserRequest req = new UserRequest();
////    	 Member req = new Member();
//		login req = new login();
//		req.setLoginId("admin");
//		req.setLoginPw("admin");
//
//		// req.setLoginId("dsg"); //원하는 요청 값
//
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Object> response = restTemplate.postForEntity(uri, req, Object.class); // 리턴받고 싶은 객체.class가 온다
//		// uri에 req object를 보내서 응답은 UserResponse.class타입으로 받을 것이다!!
//
//		System.out.println(response.getStatusCode());
//		System.out.println(response.getHeaders());
//		System.out.println(response.getBody());
//
//		return response.getBody();
//
//	}

	// restTemplage exchange 구현
	public ResponseEntity exchange() {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8081").path("/api/server/{path}/header").encode()
				.build().expand("user").toUri();
		log.info("uri : {}", uri);

		UserRequest req = new UserRequest();
		req.setName("dsg");
		req.setAge(27);

		RequestEntity<UserRequest> request = RequestEntity.post(uri).contentType(MediaType.APPLICATION_JSON)
				.header("x-authorization", "my-header").body(req);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserResponse> response = restTemplate.exchange(request, new ParameterizedTypeReference<>() {
		});
		log.info("{}", response.getStatusCode());
		log.info("{}", response.getHeaders());
		log.info("{}", response.getBody());

		return response;
	}

	// restTemplage naver 구현 item 빈값으로 나옴
	public ResponseEntity naver() {
		URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com").path("/v1/search/local.json")
				.queryParam("query", "%EC%A3%BC%EC%8B%9D").queryParam("display", "10").queryParam("start", "1")
				.queryParam("sort", "random").encode().build().toUri();
		log.info("uri : {}", uri);

		RequestEntity<Void> req = RequestEntity.get(uri).header("X-Naver-Client-Id", "Zi3o1uQftp59zuIqEAz4")
				.header("X-Naver-Client-Secret", "iy6YKSWpLM").build();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(req, new ParameterizedTypeReference<>() {
		});
		log.info("{}", response.getStatusCode());
		log.info("{}", response.getHeaders());
		log.info("{}", response.getBody());

		return response;
	}

	// -------------------------------------------------------------------------------------------------------------------------다영님
	// 서버 연결 부분
	// 다영님 서버 연결 https://203.250.133.171:8000/register ==> // 다시 전송할때는 모든 정보가 달라야
	// 오류가X
//	{
//	  "nickname": "string",
//	  "login_id": "string",
//	  "login_pw": "string",
//	  "name": "string",
//	  "email": "user@example.com",
//	  "phone": "string"
//	}

//	/register/{login_id}/{login_pw}/{nickname}/{name}/{email}/{phone}

	public userJoin join(String login_id, String login_pw, String nickname, String name, String email, String phone) { // userJoin이라는
																														// 리턴
																														// 받을
																														// class를
																														// 만들어
																														// 주어야
																														// 된다

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
//				.path("/register/{login_id}/{login_pw}/{nickname}/{name}/{email}/{phone}")
//				.encode().build().expand("유성dfdfㅀㅎㅎdfㄹfㄱddddf훈", "12dffddㄱfㅎdffㅎddgㅀㅀfdf3", "123fㅀㅀdㄱfㅎㅎgfgddffddf",
//						"seonㅀㄹㅀddfdfdㅎdffdg", "tesddㅎfdfddㄹfㅀㅀdft@naver.fcom", "01ㄹdfㅎ0dddfㅀfdf2df")
				.path("/register/{login_id}/{login_pw}/{nickname}/{name}/{email}/{phone}").encode().build()
				.expand(login_id, login_pw, nickname, name, email, phone).toUri();
		System.out.println(uri.toString());

		login req = new login();
//		req.setLoginId("admin");
//		req.setLoginPw("admin");

		RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!
		ResponseEntity<userJoin> result = restTemplate.postForEntity(uri, req, userJoin.class);
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();
	}

	// 다영님 서버 allroominfo기능 --> https://203.250.133.171:8000/allRoomInfo/
	// 현재 받아올 경우 json형태가 다르다고 오류 발생 -> 받아오는 것을 [] 배열로 감싸 배열 형태로 받고 리턴해주면 오류 해결
	public allRoomInfo[] allRoomInfo() { // userJoin이라는 리턴 받을 class를 만들어 주어야 된다

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000").path("/webMethod/allRoomInfo")
				.encode().build().toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!

		ResponseEntity<allRoomInfo[]> result = restTemplate.getForEntity(uri, allRoomInfo[].class);
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();
	}

	// 다영님 서버 로그인 기능
	public login doLogin(String login_id, String login_pw) {
		ignoreHttps.ignore();

		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/login/{login_id}/{login_pw}").encode().build().expand(login_id, login_pw).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<login> result = restTemplate.getForEntity(uri, login.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();

	}

	// 다영님 서버 방이름 변경 기능(put 방식)
	public Modify doModify(String old_room_name, String new_room_name) {
		ignoreHttps.ignore();

		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/webMethod/update_roomName/{old_room_name}/{new_room_name}").encode().build()
				.expand(old_room_name, new_room_name).toUri();
		System.out.println(uri.toString());

		// ----------------------------------------
		// 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// 수정할 정보 셋팅
		modifyInfo info = new modifyInfo();
		info.setOld_room_name(old_room_name);
		info.setNew_room_name(new_room_name);

		Modify modify = new Modify();

		HttpEntity<modifyInfo> request = new HttpEntity<>(info, headers);
		// ----------------------------------------

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Modify> result = restTemplate.exchange(uri, HttpMethod.PUT, request, Modify.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();

	}

	// 다영님 서버 특정 방이름 찾기
	public allRoomInfo[] findRoom(String roomName) { // userJoin이라는 리턴 받을 class를 만들어 주어야 된다

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/webMethod/findRoomInfo/{room_name}").encode().build().expand(roomName).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!

		ResponseEntity<allRoomInfo[]> result = restTemplate.getForEntity(uri, allRoomInfo[].class);
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();
	}

	// 다영님 서버랑 연결 - 특정 방이름을 넘겨주면 해당방에 대한 데이터를 모두 삭제 해주는 역할
	public Result deleteRoomData(String roomName) {

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000").path("webMethod/delete_room/{room_name}")
				.encode().build().expand(roomName).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();

		// --------------------------------------------------------------------
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);

		// --------------------------------------------------------------------

		ResponseEntity<Result> result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, Result.class);
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();

	}

	public userInfo[] showUserInfo(String userId) {

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/webMethod/userInfo/{login_id}").encode().build().expand(userId).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<userInfo[]> result = restTemplate.getForEntity(uri, userInfo[].class);
//		        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();

	}

	// 다영님 서버랑 연결 : 디데일 에서 해당 방에 대한 정보를 페이징 기능을 위해 해당 페이징에 맞게 5개 데이터씩만 보내주기
	public allRoomInfo[] showDetailData(String roomName, int start, int amount) { // userJoin이라는 리턴 받을 class를 만들어 주어야 된다

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/webMethod/stat_web/{room_name}/{start}/{amount}").encode().build()
				.expand(roomName, start, amount).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!

		ResponseEntity<allRoomInfo[]> result = restTemplate.getForEntity(uri, allRoomInfo[].class);
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();
	}

	// 다영님 서버랑 연결 하기 : 날짜 검색 기능
	public allRoomInfo[] showSearchReDate(String searchText, String roomName, int start, int amount) {

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/webMethod/findDate/{searchText}/{room_name}/{start}/{amount}").encode().build()
				.expand(searchText, roomName, start, amount).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
//		        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!

		ResponseEntity<allRoomInfo[]> result = restTemplate.getForEntity(uri, allRoomInfo[].class);
//		        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();

	}

	// 다영님 서버 연결 미세먼지 검색 기능
	public allRoomInfo[] showSearchFindDust(String searchText, String roomName, int start, int amount) {

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/webMethod/findFinedust/{searchText}/{room_name}/{start}/{amount}").encode().build()
				.expand(searchText, roomName, start, amount).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
//		        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!

		ResponseEntity<allRoomInfo[]> result = restTemplate.getForEntity(uri, allRoomInfo[].class);
//		        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();

	}
	
	
	
	//다영님 서버 온도 검색 기능 
	public allRoomInfo[] showSearchFindTemp(String searchText, String roomName, int start, int amount) {

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/webMethod/findTemp/{searchText}/{room_name}/{start}/{amount}").encode().build()
				.expand(searchText, roomName, start, amount).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
//		        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!

		ResponseEntity<allRoomInfo[]> result = restTemplate.getForEntity(uri, allRoomInfo[].class);
//		        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();

	}
	
	
	
	
	//다영님 서버연결 : 습도에 대한 검섹 
	public allRoomInfo[] showSearchFindHumidity(String searchText, String roomName, int start, int amount) {

		// https인증 무시 하는 코드를 선언하여 먼저 연결전 실행 시켜야 한다.
		ignoreHttps.ignore();

		// 데이터 보낼때 중복 값 보내면 에러
		URI uri = UriComponentsBuilder.fromUriString("https://203.250.133.171:8000")
				.path("/webMethod/findHumidity/{searchText}/{room_name}/{start}/{amount}").encode().build()
				.expand(searchText, roomName, start, amount).toUri();
		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
//		        String result = restTemplate.getForObject(uri, String.class);
		// json 형태로 받자!

		ResponseEntity<allRoomInfo[]> result = restTemplate.getForEntity(uri, allRoomInfo[].class);
//		        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());

		return result.getBody();

	}
	

}