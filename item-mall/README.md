# item-mall

7번 강의 `스프링 MVC - 웹 페이지 만들기`를 혼자 다시 구현해보는 실습 프로젝트입니다.

## 현재 구현 범위

- 상품 목록 조회
- 상품 상세 조회
- 상품 등록
- 상품 수정
- 상품 삭제
- 등록/수정 후 redirect 적용

## 구조

- `web/basic`
  화면 요청을 받는 MVC 컨트롤러
- `web/basic/dto/request`
  폼 요청 바인딩용 request DTO
- `service/item`
  상품 관련 서비스 로직
- `service/item/command`
  서비스 계층으로 넘기는 command 객체
- `domain/Item`
  도메인 객체와 메모리 저장소 역할의 repository

## 이번에 중요하게 본 포인트

- 컨트롤러는 HTTP 요청/응답과 뷰 반환에 집중한다.
- 서비스는 비즈니스 흐름과 예외 판단을 담당한다.
- request DTO는 web 계층에서만 사용하고, service에는 command로 넘긴다.
- repository는 web/service DTO를 직접 알지 않도록 유지한다.
- 등록/수정 후에는 `redirect:`를 사용해서 PRG 흐름으로 처리한다.
- 삭제는 `GET`이 아니라 `POST` form 전송으로 처리한다.

## 실행

```powershell
.\gradlew.bat bootRun
```

기본 포트는 `application.properties` 설정을 따릅니다.

## 다음에 해볼 것

- Bean Validation 적용
- 에러 메시지 화면 표시
- API 전용 컨트롤러와 response DTO 설계
- 장바구니/주문 등 쇼핑몰 기능 확장
