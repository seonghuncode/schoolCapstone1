package RetropitTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

	public static void main(String[] args) {

		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
		
				
				.addConverterFactory(GsonConverterFactory.create()) //json을 변환해줄 Gson변환기 등록
				.build();

		RetrofitService service1 = retrofit.create(RetrofitService.class);
		//Retrifit인스턴스 인터페이스 객체 구현
		Call<PostResult> call = service1.getPosts("2");
		//사용할 메서드 선언
		
		call.enqueue(new Callback<PostResult>() {
			//enqueue로 비동기 통신 실행, 통신완료 후 이벤트 처리를 위한 Callback리스너 등록
			@Override
			public void onResponse(Call<PostResult> call, Response<PostResult> response) {
				if (response.isSuccessful()) {
					PostResult result = response.body();
					System.out.println("=============================================================");
					System.out.println("=============================================================");
					System.out.println("=============================================================");
					System.out.println("=============================================================");
					System.out.println("=============================================================");
					System.out.println("=============================================================");
					System.out.println("=============================================================");
					System.out.println(result);
				}else {
					System.out.println("실패");
				}

			}

			@Override
			public void onFailure(Call<PostResult> call, Throwable t) {
				// TODO Auto-generated method stub

			}

		});

	}

}
