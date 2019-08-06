# NaverMovie

네이버 검색 API를 활용한 아이키텍처 장난감 프로젝트 입니다.

## 사용법

1. 네이버 검색 API의 Client ID 와 Client Secret 값을 신청해 주세요.</br>
[오픈 API 이용 신청](https://developers.naver.com/docs/search/movieItemEntity/)

2. app 폴더 안에 gradle.properties 파일을 생성해 줍니다.

3. gradle.properties 안에 MOVIE_CLIENT_ID, MOVIE_CLIENT_SECRET 아래와 같이 력해 주세요.
```kotlin
MOVIE_CLIENT_ID = "Client_ID"
MOVIE_CLIENT_SECRET = "Client_Secret"
```

## Model편

1. CleanArchitecture 적용 : Prenent, Domain, Data 레이어 구분</br>
2. Model : Domain과 Data 레이어</br></br>
[정상에서 IT를 외치다](https://black-jin0427.tistory.com/225)
