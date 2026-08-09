# hello-servlet

강의 `서블릿`, `JSP`, `MVC 패턴`, `FrontController` 흐름에 맞춰 바로 실습할 수 있는 프로젝트입니다.

## 실행

IntelliJ에서 `hello-servlet` 폴더를 열고 `HelloServletApplication`을 실행하면 됩니다.

또는 PowerShell:

```powershell
.\gradlew.bat bootRun
```

## 주요 패키지

- `hello.servlet.basic`
- `hello.servlet.domain.member`
- `hello.servlet.web.servlet`
- `hello.servlet.web.jsp`
- `hello.servlet.web.frontcontroller`

## 주요 주소

- 홈: `http://localhost:8080`
- basic 서블릿: `http://localhost:8080/hello-servlet`
- servlet 회원 폼: `http://localhost:8080/servlet/members/new-form`
- jsp 회원 폼: `http://localhost:8080/jsp/members/new-form`
- front controller v1 회원 폼: `http://localhost:8080/front-controller/v1/members/new-form`
