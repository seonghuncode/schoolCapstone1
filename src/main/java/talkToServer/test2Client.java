package talkToServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class test2Client {

	public static void main(String[] args) {
	//서버와 통신할 수 있는 Socket만들고 서버접속
		Socket server = null;
		InputStream is = null; //서버와 input통신을 할수있는 스트림(IO)
		DataInputStream dis = null; //최종적으로 서버와 통신
		
		OutputStream os = null; //서버와 output통신을 할수있는 스트림(IO)
		DataOutputStream dos = null; //최종적으로 서버와 통신
		try {
			server = new Socket("127.0.0.1",12345); //cmd에서 ipconfig찍으면 나오는 내IP
//			server = new Socket("203.250.133.171", 8000);
			
			//서버가 전송하는 데이터를 읽기위한 스트림객체를 Socket으로부터 생성
			is = server.getInputStream(); //전송하는 데이터를 읽기 위한 
			dis = new DataInputStream(is);
			//서버로 전송할 데이터를 출력하기위한 스트림객체를 Socket으로부터 생성
			os = server.getOutputStream(); //전송한느 데이터를 출력하기 위한
			dos = new DataOutputStream(os);
			//여기까지가 준비 작업
			
			
			// 1.client <= server 서버가 전송한 데이터 읽기 
			//순서가 중요 - thread돌리는게 아니라서 output 2번하면 client에서도 똑같이 input 2번
			String UTFMsg = dis.readUTF();
			System.out.println("서버가 전송한 데이터 1: "+UTFMsg);
			int intMsg = dis.readInt();
			System.out.println("서버가 전송한 데이터 2: "+intMsg);
	
			
			// 2. client => server 클라이언트에서 서버로 전송
			dos.writeUTF("안녕하세요 서버님 클라이언트입니다.");
			
		}catch (IOException e) {
			System.out.println("오류 발생");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
