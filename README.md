## 🕐 교육 기간
2023.07.20 ~ 2023.10.26
<br>
<br>

## ⛏️ 기술스택
<table>
    <tr>
        <th>구분</th>
        <th>내용</th>
    </tr>
    <tr>
        <td>Language</td>
        <td>
          <img src="https://img.shields.io/badge/Kotlin-0095D5?style=flat-square&logo=Kotlin&logoColor=white"/></a>
        </td>
    </tr>
</table>

## 🔍 화면설명
  - ☕ Fragment와 RecyclerView를 이용한 카페주문 화면
  - 💛 Rest Api를 활용해 사진 데이터 전달받기 (Unsplash)
  - 💡 개별화된 Holder로 Adapter를 구성하여 채팅형식의 뷰 구성
  - 🙆‍♀️ 카메라권한을 받고 사진을 저장하기  <br>

## 🖥️ 화면구성
### 🖥️메인 activity
<img src="https://github.com/rlawlgp0197/App/assets/134493927/68af40d7-bc9f-47a8-ae7d-eaf4c651098d.jpg"  width="200" height="400"/>

<br>
<br>

### ☕카페주문
- 메뉴는 data class로 생성
- 메뉴를 클릭하면 dialog를 통해 옵션을 선택할수 있다.
- 옵션 선택후 done을 클릭하면 toast창으로 선택한 메뉴를 알려준다.
- 사용자가 고른 메뉴의 데이터가 fragment창으로 넘어가며 내가 선택한 메뉴, 옵션들을 보여준다
<img src="https://github.com/rlawlgp0197/App/assets/134493927/6e30365f-7098-4137-84e2-12d50135e0aa.jpg"  width="200" height="400"/>
<img src="https://github.com/rlawlgp0197/App/assets/134493927/64a9f5bb-ec0b-4bb1-82ee-9b0a14ed2cc3.jpg"  width="200" height="400"/>
<img src="https://github.com/rlawlgp0197/App/assets/134493927/d767ebf6-650e-4619-b14d-285b0af14bbc.jpg"  width="200" height="400"/>

<br>
<br>

### 🖼갤러리
- Rest api를 이용해 사진데이터 받아오기
- 검색창에 원하는 키워드를 입력하면 키워드에 맞는 랜덤사진을 30장을 보여준다
- RecyclerView 활용
<img src="https://github.com/rlawlgp0197/App/assets/134493927/84663cc9-f6b6-45d1-ab4d-20edc277fa21"  width="200" height="400"/>

<br>
<br>


