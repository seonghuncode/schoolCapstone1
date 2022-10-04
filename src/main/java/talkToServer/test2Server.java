package talkToServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//
class person{
	int age;
	String name;
	
	public person(int age,String name) {
		this.age = age;
		this.name = name;
	}
}

public class test2Server {
	

	public static void main(String[] args) {
		
		//
		ArrayList<person> people = new ArrayList<>();
		person p1 = new person(20, "홍길동");
		person p2 = new person(22, "춘향이");
		people.add(p1);
		people.add(p2);
		
		System.out.println("======================");
		System.out.println(people);
		System.out.println(p1);
		System.out.println("======================");
		
		
		Socket client = null;
		InputStream is = null; //클라이언트와 input통신을 할수있는 스트림(IO)
		DataInputStream dis = null; //최종적으로 클라이언트와 통신
		
		OutputStream os = null; //클라이언트와 output통신을 할수있는 스트림(IO)
		DataOutputStream dos = null; //최종적으로 클라이언트와 통신
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("ServerSocket준비완료..클라이언트의 접속을 기다림");
			// 대기하다가 (Listen) 클라이언트 접속하면 클라이언트정보를 Socket객체로 만들어서 리턴
			while (true) {
				client = server.accept();
				//클라이언트가 전송하는 데이터를 읽기위한 스트림객체를 Socket으로부터 생성
				is = client.getInputStream();
				dis = new DataInputStream(is);
				//클라이언트로 전송할 데이터를 출력하기위한 스트림객체를 Socket으로부터 생성
				os = client.getOutputStream();
				dos = new DataOutputStream(os);
				//여기까지가 준비 작업
				
				//클라이언트로 정보 보내기
				
				
				
				// 1. server => client 
				//순서가 중요 - thread돌리는게 아니라서 output 2번하면 client에서도 똑같이 input 2번
				dos.writeUTF(client.getInetAddress().getHostAddress()+"님 접속을 환연합니다.");
				dos.writeInt(20000);
				dos.writeInt(500);
				
				
				// 2. server <= client
				String clientMsg = dis.readUTF();
				System.out.println("클라이언트가 전송한 데이터 : "+clientMsg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
