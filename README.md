### 사용 기술 스택 요약
- 아키텍쳐 : MVVM, LiveData
- 비동기 처리 : Coroutine, Flow
- 의존성 주입 : Koin
- 그 외 : Jetpack Paging, Tab Layout, ViewPager2, Secrets-Gradle-Plugin, Parcelize

---

### 코드 설명

#### xml
- activity_main.xml
  - 메인 액티비티에는 TabLayout와 ViewPager2를 배치
  - ![img1](https://user-images.githubusercontent.com/68932465/210925496-6fff75db-bf70-440a-b998-859a0004e0cd.PNG)

- fragment_board_list.xml
  - ViewPager에 배치될 프래그먼트에는 RecyclerView만 배치
  - ![img1](https://user-images.githubusercontent.com/68932465/210925699-99657fde-2247-4333-a765-57b1018879e6.PNG)
 
- item_broad.xml
  - 리사이클러 뷰에 들어갈 방송 리스트 item
  - CardView를 통해 카드 형식 이미지 배치
  - 원형 프로필 이미지를 보여주기 위해 외부 라이브러리 사용
  - ![12621621](https://user-images.githubusercontent.com/68932465/210926064-bb8796a5-e05a-458c-adb8-d7b18dbafa6b.PNG)
![123123](https://user-images.githubusercontent.com/68932465/210926067-63666360-1073-4788-94fc-fdff631d9a51.PNG)

#### 패키지 계층 구조
- ![16122126126](https://user-images.githubusercontent.com/68932465/210926276-b0197a2f-f75b-4a0f-9eb6-49ac3bcf29c7.PNG)
- Clean Architecture 기반 구현을 위한 data, domain, presentation 패키지 분할
- 의존성 주입을 위한 di 패키지 생성

#### data 레이어
- ApiService
  - 서버와의 통신을 위한 Query문 작성
  - client_id는 위험 정보이므로 Secrets-Gradle-Plugin을 사용하여 local.properties에 숨기기
  - 방송 리스트 불러오는 함수에서는 페이징 처리를 위해 페이지번호와 선택한 카테고리의 번호를 인자로 전달
  - 카테고리 리스트 불러오는 함수에서는 아무런 인자가 필요 없음
  - 코루틴 비동기 처리를 위한 suspend 키워드 선언
  - ![3get21q](https://user-images.githubusercontent.com/68932465/210926502-d4fccd68-ced3-4509-a45d-133ca0c65530.PNG)

- ProvideAPI
  - 싱글턴 패턴과 중복 방지를 위해 retrofit관련 객체를 작성한 파일
  - ###싱글턴 패턴을 사용해 메모리 절약 도모
  - ![1622412](https://user-images.githubusercontent.com/68932465/210926982-8b4361a9-c305-4db5-bd49-b0e8512d58bc.PNG)

- AppRepository
  - Network 통신을 위한 로직의 인터페이스를 작성한 파일
  - ![126126123132](https://user-images.githubusercontent.com/68932465/210927109-f74edb5c-501b-47d6-bb87-e0eccdc1d721.PNG)

- AppRepositoryImpl
  - Repository의 구현체가 담겨있는 클래스
  - ApiService 인터페이스와 코루틴 디스페이스를 인자로 주입받음
  - 방송 리스트는 페이징을 통해 불러옴, 개수는 20개로 정함
  - 카테고리 정보는 페이징 처리 없이 단순하게 불러옴
  - ![16621612](https://user-images.githubusercontent.com/68932465/210927323-74abe491-3afb-4b7b-a25b-7488fe05717a.PNG)

- BroadListPagingSource
  - 페이징 처리를 위한 페이징 소스 클래스
  - 마지막 페이지를 불러온 경우 nextKey를 null로 통해 그만 불러오기
  - ![1221323123123123](https://user-images.githubusercontent.com/68932465/210927547-784ef28c-69ad-4bbd-8b63-0d12163bb1fd.PNG)

- response Package
  - 안드로이드 스튜디오의 Kotlin data class File from JSON 
  - Broad클래스는 추후의 인텐트 객체로 넘기기 위해 Parcelize 어노테이션 설정
  
### di 레이어
- AppModule
  - 적절한 Koin의 사용으로 객체를 주입하기 위한 코드 작성
  - ![15213231](https://user-images.githubusercontent.com/68932465/210927845-d3e8c4cd-b455-4839-a79c-0788fa9773b1.PNG)

### domain 레이어
- 적절한 도메인 층의 구현을 위해 Usecase를 만들었으나 복잡하지 않은 과제로 인해 단순한 invoke 함수만 작성
- Repository를 인자로 받아 Repository의 Api 사용
- ![6162126](https://user-images.githubusercontent.com/68932465/210928020-93db1948-9335-4d90-a893-3c9f3ff8acfc.PNG)

### presentation 레이어
- 각 화면별로 패키지를 나누어 각 패키지는 액티비티, 뷰모델, State 클래스를 포함
  - adapter 패키지는 뷰페이저 활용을 위한 Fragment 어댑터, 페이징 사용을 위한 Paging 어댑터 클래스 포함
  - base 패키지는 각 컨트롤러, 뷰모델의 기반 클래스 포함. 필수적이고 공통적인 코드가 포함됨.
  - ![162612513](https://user-images.githubusercontent.com/68932465/210928297-164dda40-f644-4468-b57e-a62243ed97f4.PNG)
- Main 패키지
  - 메인 액티비티에서는 방송 카테고리 리스트를 불러옴
  - 카테고리 개수만큼 동적으로 프래그먼트를 생성
  - 카테고리 리스트는 State 패턴을 사용.
  - ViewModel의 카테고리 State를 observe 하여 자동으로 UI 업데이트를 도모
  - 메인 액티비티에서는 뷰페이저와 탭레이아웃의 연동 및 탭레이아웃의 클릭 리스너를 연결하여 자동으로 카테고리 별 방송 리스트 조회
  - ![162612612612](https://user-images.githubusercontent.com/68932465/210928653-13e8e8de-c9e0-427b-b7d7-5974c15c4174.PNG)
![123123123](https://user-images.githubusercontent.com/68932465/210928656-e2444f4e-ba22-4c32-9114-af14e03ac7d8.PNG)
![52353254](https://user-images.githubusercontent.com/68932465/210928659-ca6b800e-2d68-4ca1-8f89-c4ed02dc5d4e.PNG)

  

