//package testRestTemplate;
//
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//public class test1 {
//    public static void main(String[] args) {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(5000);  // 읽기시간초과, ms
//        factory.setConnectTimeout(3000); // 연결시간초과, ms
//        HttpClient httpClient = HttpClientBuilder.create()
//            .setMaxConnTotal(100) // connection pool 적용
//            .setMaxConnPerRoute(5) // connection pool 적용
//            .build();
//        factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅
//        RestTemplate restTemplate = new RestTemplate(factory);
//
//        String url = "http://testapi.com/search?text=1234"; // 예제니까 애초에 때려박음..
//
//        Object obj = restTemplate.getForObject("", "응답내용과 자동으로 매핑시킬 java object");
//        System.out.println(obj);
//    }
//}
//
