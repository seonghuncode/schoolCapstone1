package ServerTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;




public class RestAPI {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		System.out.println("[HttpURLConnection 사용해  post body json 방식 데이터 요청 및 응답 값 확인 실시]");

		/*
		 * [설 명] 1. HttpURLConnection은 http 통신을 수행할 객체입니다 2. URL 객체로 connection을 만듭니다 3.
		 * 응답받은 결과를 InputStream으로 받아서 버퍼에 순차적으로 쌓습니다
		 */

		// 데이터 정의 실시
//		String url = "http://203.250.133.171:8000/register";
		String url = "http://localhost:8081/machine/room/getRoom?roomname=\"거실\" ";

//		String data = "{ \"userId\" : \"1\", \"id\" : \"1\" }"; //json 형식 데이터
		String data = "{ \"roomname\" : \"거실\" }";

		// 메소드 호출 실시
		httpPostBodyConnection(url, data);

	}// 메인 종료


	
	public static void httpPostBodyConnection(String UrlData, String ParamData) {
		

		// http 요청 시 필요한 url 주소를 변수 선언
		String totalUrl = "";
		totalUrl = UrlData.trim().toString();

		// http 통신을 하기위한 객체 선언 실시
		URL url = null;
		HttpURLConnection conn = null;

		// http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
		String responseData = "";
		BufferedReader br = null;
		StringBuffer sb = null;

		// 메소드 호출 결과값을 반환하기 위한 변수
		String returnData = "";

		try {
			// 파라미터로 들어온 url을 사용해 connection 실시
			url = new URL(totalUrl);
			conn = (HttpURLConnection) url.openConnection();

			// http 요청에 필요한 타입 정의 실시
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8"); // post body json으로 던지기 위함
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true); // OutputStream을 사용해서 post body 데이터 전송
			try (OutputStream os = conn.getOutputStream()) {
				byte request_data[] = ParamData.getBytes("utf-8");
				os.write(request_data);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// http 요청 실시
			conn.connect();
			System.out.println("http 요청 방식 : " + "POST BODY JSON");
			System.out.println("http 요청 타입 : " + "application/json");
			System.out.println("http 요청 주소 : " + UrlData);
			System.out.println("http 요청 데이터 : " + ParamData);
			System.out.println("");

			// http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			sb = new StringBuffer();
			while ((responseData = br.readLine()) != null) {
				sb.append(responseData); // StringBuffer에 응답받은 데이터 순차적으로 저장 실시
			}

			// 메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
			returnData = sb.toString();

			// http 요청 응답 코드 확인 실시
			String responseCode = String.valueOf(conn.getResponseCode());
			System.out.println("http 응답 코드 : " + responseCode);
			System.out.println("http 응답 데이터 : " + returnData);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}// 클래스 종료클래스 종료