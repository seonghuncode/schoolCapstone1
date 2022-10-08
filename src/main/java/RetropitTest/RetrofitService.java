package RetropitTest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
	//interface정의 - 사용할 메소드 선언
	//@GET, @POST, @PUT, @DELETE, @HEAD중에 어떠한 작업을 할 것 인가
	//@GET("posts/{post}") : posts : 전체 url에서 url을 제외한 end point 부분 

	// @GET( EndPoint-자원위치(URI) )
   	@GET("posts/{post}")
 	Call<PostResult> getPosts(@Path("post") String post);
   	
   
   	 
}