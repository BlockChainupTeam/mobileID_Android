# mobileID_Android
모바일 신분증 - 클레이튼/안드로이드 사용

## History
  - 09-15 : Android Studio 프로젝트 생성 및 Caver-java 추가, 각 api테스트 예제
  
## version
  - Android SDK : Android 10.0+ / api Level 30
  - JDK : jdk1.8.0_261
  - AVD (Android Virtual Device) : Android 10.0+ / api Level 30
  - Caver-java : 1.5.3-rc.1-android
  
## Usage(임시)
  - app\src\main\res\raw 경로에 배포한 컨트랙트 abi랑 address 위치시킬것
  - 가상디바이스 패키지경로 "/data/data/com.example.mobileid/keystore.json" 에 keystore 파일 위치시켜야 구동됨.
      + shift 두번 눌러서 검색창 나오면 Device File Explorer
      + 이후 해당 경로에 가서 우클릭하고 Upload 누르면 가상장치경로에 파일전송가능. 
