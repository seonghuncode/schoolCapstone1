# schoolCapstone1
3학년2학기 캡스톤

# 웹웹.

<br><br><br>
<h2 tabindex="-1" dir="auto"><a id="user-content--프로젝트-구성도" class="anchor" aria-hidden="true" tabindex="-1" href="#-프로젝트-구성도"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path d="m7.775 3.275 1.25-1.25a3.5 3.5 0 1 1 4.95 4.95l-2.5 2.5a3.5 3.5 0 0 1-4.95 0 .751.751 0 0 1 .018-1.042.751.751 0 0 1 1.042-.018 1.998 1.998 0 0 0 2.83 0l2.5-2.5a2.002 2.002 0 0 0-2.83-2.83l-1.25 1.25a.751.751 0 0 1-1.042-.018.751.751 0 0 1-.018-1.042Zm-4.69 9.64a1.998 1.998 0 0 0 2.83 0l1.25-1.25a.751.751 0 0 1 1.042.018.751.751 0 0 1 .018 1.042l-1.25 1.25a3.5 3.5 0 1 1-4.95-4.95l2.5-2.5a3.5 3.5 0 0 1 4.95 0 .751.751 0 0 1-.018 1.042.751.751 0 0 1-1.042.018 1.998 1.998 0 0 0-2.83 0l-2.5 2.5a1.998 1.998 0 0 0 0 2.83Z"></path></svg></a>💁 프로젝트 설명</h2>
이번 캡스톤 프로젝트를 진행하면서 웹 개발의 경우 Java 언어와 Spring Boot 프레임 워크를 사용하여 개발하였으며 개발 툴로는 Spring Tool Suit를 사용하여 개발하였고 Rest API를 통해 온도, 습도, 미세먼지, LED 색상의 위치에 대한 데이터를 요청하여 받아와 데이터를 가공하며 페이지를 동적으로 움직이기 위해 JSP를 사용하여 JSTL 문법을 함께 사용하여 페이지를 동적으로 움직이도록 구현하였습니다. 디자인의 경우 bootstrap과 tailwind를 사용하였고 폰트의 경우 폰트어썸에서 가지고 와 사용하였습니다.
프로젝트를 진행하면서 Rest API에 요청으로는 회원가입 기능을 post 방식, DB에 저장된 모든 방에 대한 정보에 대한 요청은 get 방식, 로그인에 대한 요청은 get 방식, 방 이름 수정에 대한 요청은 put 방식, 특정 방을 찾는 요청의 경우 get 방식, 삭제의 경우 delete 방식, DB에 저장된 사용자에 대한 정보 요청은 get 방식, 특정 방에 대한 정보 요청은 get 방식, 검색 기능에 대해서는 get 방식을 사용해서 rest API에 요청하여 사용했습니다. 이때 Rest API를 사용하면서 https로 요청할 경우 SSL 인증서가 신뢰하는 기관 인증서가 없거나 SSL/TLS 암호화 버전이 맞지 않는 경우 발생하는 오류 코드가 발생하여 정상적으로 요청이 진행되지 않았습니다. 이러한 문제를 해결하기 위해서는 key store에 신뢰하는 인증기관 인증서를 추가하거나 서버, 클라이언트 사이에 사용하는 TLS 버전을 동일하게 해주어야 하는데 이때 해결방안으로는 ignoreHttps파일을 만들어서 https 요청 시 오류를 무시하고 요청을 강제로 진행하는 코드를 작성하여 강제적으로 요청을 실행하도록 하여 문제를 해결하였습니다.
또한 Rest API를 통해 요청한 데이터를 가공하여 그래프로 나타내는 과정에 있어 처음에는 basic line chart API를 사용하였으나 사용이 간단하다는 이점을 제외하고는 여러 데이터의 통계를 나타내는 것에 있어 적합하지 않다고 판단되어 구글 차트 API를 사용하게 원형 그래프와 막대그래프로 나타내어 통계 그래프를 만들어 표현하였습니다.

<br><br><br>


<table>
<thead>
<tr>
<th align="center">https요청에 대한 인증서 문제를 무시하고 요청을 보내는 코드
(해결 방안에 대한 추가 자료)
</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/459b4fd3-62f2-43fb-8bd7-bf625ef7fa39" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/b08df925-eb43-4f99-9e17-4a43b4ac879c"><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/b08df925-eb43-4f99-9e17-4a43b4ac879c" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/e657c137-1df8-4da9-8bb1-7f72d10a2592"><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/e657c137-1df8-4da9-8bb1-7f72d10a2592" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>



<br><br><br>


<h2 tabindex="-1" dir="auto"><a id="user-content--프로젝트-구성도" class="anchor" aria-hidden="true" tabindex="-1" href="#-프로젝트-구성도"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path d="m7.775 3.275 1.25-1.25a3.5 3.5 0 1 1 4.95 4.95l-2.5 2.5a3.5 3.5 0 0 1-4.95 0 .751.751 0 0 1 .018-1.042.751.751 0 0 1 1.042-.018 1.998 1.998 0 0 0 2.83 0l2.5-2.5a2.002 2.002 0 0 0-2.83-2.83l-1.25 1.25a.751.751 0 0 1-1.042-.018.751.751 0 0 1-.018-1.042Zm-4.69 9.64a1.998 1.998 0 0 0 2.83 0l1.25-1.25a.751.751 0 0 1 1.042.018.751.751 0 0 1 .018 1.042l-1.25 1.25a3.5 3.5 0 1 1-4.95-4.95l2.5-2.5a3.5 3.5 0 0 1 4.95 0 .751.751 0 0 1-.018 1.042.751.751 0 0 1-1.042.018 1.998 1.998 0 0 0-2.83 0l-2.5 2.5a1.998 1.998 0 0 0 0 2.83Z"></path></svg></a>✏️ 프로젝트 각 역할 별 기능 설명</h2>

<table>
<thead>
<tr>
<th align="center">메인화면
</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/b88bf641-a2db-4d82-aa69-2e4755c25597" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>

<table>
<thead>
<tr>
<th align="center">로그인 전
</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/44e17031-fad0-47c5-b5d7-1c399779dcfa" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>


<table>
<thead>
<tr>
<th align="center">로그인 후
</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/1f3394a8-4da6-4df0-8c3d-5c8c3af3f8bc" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>

<table>
<thead>
<tr>
<th align="center">회원가입 (과정)
</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/35568145-18c1-4779-99ea-caa73751169c" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/1a653bd1-b746-4637-8020-c9981e9630df" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/4dcdbc28-918d-443a-9196-6b0b6e764599" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/3c332a57-82f3-4d30-8aad-2da05d31eff2" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>



<table>
<thead>
<tr>
<th align="center">로그인
</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/a05f6e8e-eafe-4d0b-b95d-edaddf94c2b3" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/18734f4f-762b-45f6-bc72-c166b635af94" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/71e0aee0-665c-4d92-873b-b8765730d1cc" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>


<table>
<thead>
<tr>
<th align="center">정보
</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/7686355f-7022-42d3-bcc9-ecae3bd4da3a" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/f4071bcf-f557-43ec-b308-d4fe086ba58e" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/f28f46e5-0f4e-4964-94e4-2edcc01ab7d3" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>



<table>
<thead>
<tr>
<th align="center">데이터 정보
</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/41bed865-eb2b-4cf3-a07d-bf64157b01c1" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/99b6bfe6-5373-41e7-b11c-c3ddb97dacaa" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/c976cb80-058f-4ea1-82a4-037083fd80a8" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/6e37afd4-66d9-40ad-902a-35216eee75b0" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
설명
1.	데이터 정보 에서 특정 방을 선택 하면 해당 방에 저장된 온도, 습도, 미세먼지, led에 대해 저장된 모든 데이터를 보여준다.
2.	또한 해당 방에 대해 맵핑된 지도를 보여주어 방의 구조를 사용자에게 보여준다.
(※위의 지도 경우 하드웨어가 C403을 돌아다니며 맵핑한 정보를 서버로부터 받아 나온 지도 입니다.)
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/7ef022a5-cbe4-4f75-a887-476c85a66881" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
<tr>
<td align="center"><a target="_blank" rel="noopener noreferrer" href=""><img src="https://github.com/seonghuncode/schoolCapstone1/assets/93322852/2bd3de2e-7bc2-43c1-9e47-754a69ef68bb" alt="Architecture" width="1100px" style="max-width: 100%;"></a></td>
</tr>
</tbody>
</table>




<br><br><br>
<h2 tabindex="-1" dir="auto"><a id="user-content--프로젝트-구성도" class="anchor" aria-hidden="true" tabindex="-1" href="#-프로젝트-구성도"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path d="m7.775 3.275 1.25-1.25a3.5 3.5 0 1 1 4.95 4.95l-2.5 2.5a3.5 3.5 0 0 1-4.95 0 .751.751 0 0 1 .018-1.042.751.751 0 0 1 1.042-.018 1.998 1.998 0 0 0 2.83 0l2.5-2.5a2.002 2.002 0 0 0-2.83-2.83l-1.25 1.25a.751.751 0 0 1-1.042-.018.751.751 0 0 1-.018-1.042Zm-4.69 9.64a1.998 1.998 0 0 0 2.83 0l1.25-1.25a.751.751 0 0 1 1.042.018.751.751 0 0 1 .018 1.042l-1.25 1.25a3.5 3.5 0 1 1-4.95-4.95l2.5-2.5a3.5 3.5 0 0 1 4.95 0 .751.751 0 0 1-.018 1.042.751.751 0 0 1-1.042.018 1.998 1.998 0 0 0-2.83 0l-2.5 2.5a1.998 1.998 0 0 0 0 2.83Z"></path></svg></a>🔍 참고 사항</h2>
[Java] HttpsURLConnection (tistory.com)
HttpsURLConnection을 이용한 통신시 SSL 인증서 수동으로 지정하는 방법 | (osujin.github.io)
Tomcat SSL 적용시 https로 자동 리다이렉트 설정하기 (tistory.com)
SSL 인증서 없이 https 통신하는 법 예제 (tistory.com)
htttps 호출시 SSL 무시하여 오류안나게 하는법 :: 나를 정리하는 공간 (tistory.com)





















