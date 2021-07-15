# Calendar API Server for Korean

<br/>
<br/>

## About
* 남들 많이 쓰는 어떤 휴일 데이터 API 못 미더워서 직접 ***양력, 음력, 휴일*** 계산해서 API 만듬.

* JAVA의 Calendar, IBM의 ChineseCalendar 모듈 사용

* 안 퍼오고 제가 만들었는데 필요하면 쓰세요. ChineseCalendar 모듈 저작권은 모르겠음.

* H2 DB에 기본적인 휴일 데이터(월, 일, 양/음력)를 입력한다. 물론 schema.sql에 있음.

* 이 API 서버 목적에는 굳이 일반 DB를 쓸 이유를 못 느낌. Mem DB로도 충분히 사용 가능하다고 생각함.



<br/><br/>

## How to Use
- 총 2개의 API가 존재

<br/>

1. 달력 데이터 API

```
# 달력 API Request

[POST] http(s)://yourURL/calendar 

{
    
	"year": 2021, // (Number) year만 필수
    	"month: 8,   // (Number) month 넣으면 해당 달 데이터만 받아옴.
	"specificDay": [ // (Array) specificDay는 기본 휴일제외한 커스텀 휴일 데이터 넣기.
		{
			"dateName": "대체 휴일", // (String) 휴일 명
			"month": 8, // (Number) 월 입력
			"day": 16  // (Number)  일 입력
		},
		{
			"dateName": "노는날",
			"month": 8,
			"day": 14
		}
	]
}



# 달력 API Response

[
  [
    [
      {
        "solar": {      // 양력 데이터
          "solarDay": 1,
          "solarMonth": 8,
          "solarYear": 2021,
          "fulldate": "20210801"
        },
        "lunar": {      // 음력 데이터
          "isleap": false,
          "lunarDay": 23,
          "lunarMonth": 6,
          "lunarYear": 2021,
          "fulldate": "20210623"
        },
        "isHoliday": "none", // 휴일정보
        "dayOfWeek": "일" // 요일정보
      },
      {
        "solar": {
          "solarDay": 2,
          "solarMonth": 8,
          "solarYear": 2021,
          "fulldate": "20210802"
        },
        "lunar": {
          "isleap": false,
          "lunarDay": 24,
          "lunarMonth": 6,
          "lunarYear": 2021,
          "fulldate": "20210624"
        },
        "isHoliday": "none",
        "dayOfWeek": "월"
      },
      ... 7개 배열 (일주일)
    ],
    ... 6개 배열 (한달)
  ], 
  ... 12개 배열 (1년)
]

```

<br/>

2. 휴일 정보 API 

```
# 휴일 API Request

[GET] http(s)://yourURL/api/holiday/2021

# 휴일 API Response

[
  {
    "year": 2021,
    "month": 1,
    "date": 1,
    "fullDate": "20210101",
    "dateName": "1월 1일"
  },
  {
    "year": 2021,
    "month": 3,
    "date": 1,
    "fullDate": "20210301",
    "dateName": "3·1절"
  },
  {
    "year": 2021,
    "month": 8,
    "date": 15,
    "fullDate": "20210815",
    "dateName": "광복절"
  },
  ...
]

```
