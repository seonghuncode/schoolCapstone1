package talkToServer;

import retrofit2.Call;
import retrofit2.http.GET;

interface Service {
    
	@GET("users/seonhuncode")
 	Call<UserInfo> getPosts();
}
