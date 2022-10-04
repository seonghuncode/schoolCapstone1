package RetropitTest;

import com.google.gson.annotations.SerializedName;

//REST API 응답데이터 구조
//
//{
//	"userId": 1,
//	"id": 1,
//	"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//	"body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
//}
//Rest API로 받아올 데이터를 변환하여 매핑랑 DTO클래스 선언
//REST API 응답 데이터 구조에 맞게 모델 클래스 선언 - 클래스명 상관x

public class PostResult {
    
	//JSON 데이터의 속성면과 변수명 + 타입(EX String, int, Booleand)일치 필수
	//json의 경우 @SerializedName("속성명")으로 속성명을 일치 시켜주면 변수명 다르게 가능
    @SerializedName("userId")
    private int userId;
    
    @SerializedName("id")
    private int id;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    
    private String title;
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑시켜줌  
    
    @SerializedName("body")
    private String bodyValue;

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResult{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", bodyValue='" + bodyValue + '\'' +
                '}';
    }
}