# Springboot-ureka

## 테이블 기반 스프링부트 웹 제작
1. 테이블생성 Create table ...
2. 샘플데이터 입력 insert into ...
3. Spring Starter Project 생성  yaksok0718
4. Entity 클래스 작성(테이블이름, 칼럼명에 따라)
5. Repository 인터페이스 작성
6. Controller 작성
7. 기능별 서비스 url 정의
- /yaksok/list 약속목록
- /yaksok/count 약속 갯수
- /yaksok/view/약속번호 약속 상세 데이터
- /yaksok/update/약속번호 약속 수정
- /yaksok/delete/약속번호 약속 삭제
- /yaksok/insert 약속 등록


---
내용 정리

# SQL

### ✅ SQL의 분류

- DML(Data Manipulation Language)
데이터 조작 - INSERT, UPDATE, INSERT, DELETE
- DDL(Data Definition Language)
데이터베이스 개체 조작 - CREATE, DROP, ALTER
- DCL(Data Control Language)
사용자 권한 부여 - GRANT, REVOKE, DENY

db목록보기

`show dataseses;`

mydb 데이터베이스 생성
`create datase mydb;`

mydb 데이터베이스를 사용함
`use mydb;`

### ✅ 테이블 생성

```sql
CREATE TABLE usertbl -- 회원 테이블
( userID   CHAR(8) NOT NULL PRIMARY KEY, -- 사용자 아이디(PK)
name     VARCHAR(10) NOT NULL, -- 이름
birthYear   INT NOT NULL,  -- 출생년도
addr    CHAR(2) NOT NULL, -- 출생지역명
mobile1 VARCHAR(30), -- 연락처
mDate     DATE  -- 회원 가입일
);
```

### ✅ PK(primary key) 의 목적 및 특징

1. 모든 레코드가 서로 다른 값을 가지고 있어 각 레코드를 식별할 수 있다.
2. NULL값이 존재할 수 없다.
3. 레코드의 값은 중복이 불가능하다.
4. 테이블에는 하나의 기본키만 가질 수 있지만 경우에 따라 1개 이상의 기본키를 정의할 수도 있다. (다수 열 기본 키, 복합 기본 키)

### ✅ FK(foreign key)의 목적 및 특징

1. 외부 키는 한 테이블의 필드로, 다른 테이블의 기본을 나타낸다.
2. 외래 키를 가지고 있는 테이블을 하위 테이블, 기본 키를 가지고 있는 테이블을 참조 이블 또는 상위 테이블이라고 한다.
3. 각 외부 키와 기본 키는 1:1관계로 매칭되어야 하며, 기본 키에 존재하지 않는 값이 외부키에 존재할 수 없다. → 외래 키 제한 표현으로 데이터베이스에게 두 테이블 간의 관계를 알려주어야 한다.

✅ `AUTO_INCREMENT`

- 테이블을 만들 때 해당 값을 자동으로 생성하게 해줌
- 반드시 PRIMARY KEY or UNIQUE 값을 지정해야 하고 숫자 형식만 사용 가능

### ✅ 데이터 삽입

```sql
INSERT INTO usertbl VALUES('LSG', '이승기', 1987, '서울', '0111111111', '2008-8-8');
INSERT INTO usertbl VALUES('KBS', '김범수', 1979, '경남', '0112222222', '2012-4-4');
INSERT INTO usertbl VALUES('KKH', '김경호', 1971, '전남', '0193333333', '2007-7-7');
INSERT INTO usertbl VALUES('JYP', '조용필', 1950, '경기', '0114444444', '2009-4-4');
```

`SELECT select_expr`-- 선택하고 싶은 것들(ex. 이름, 날짜 등등)
`FROM table_references`  -- 참고할 테이블 이름
`WHERE` 조건  --
`GROUP BY` 정렬기준칼럼,정렬기준칼럼,...
`HAVING` 조건
`ORDER BY` 정렬기준칼럼,정렬기준칼럼,...

중복 데이터 제거하고 출력하는 `DISTINCT`

### ✅ WHERE

where 칼럼명 between a and b
where 칼럼명 LIKE '%카페'
where 칼럼명 LIKE '카페%'
where 칼럼명 LIKE '%카페%'
where 칼럼명 LIKE '*테'
where 칼럼명 LIKE 'A*'
where 칼럼명 in (10,20,30)
where 칼럼명 IS NULL / IS NOT NULL

### ✅ GROUP BY

select절과 where 절에는 group by 한 칼럼과 집계함수만 사용가능하다.

- - userid 당 구매 갯수 총 합
SELECT **userid**, SUM(amount) FROM buytbl GROUP BY **userid**;
-- userid 당 구매 갯수의 평균
SELECT **userid**, AVG(amount) FROM buytbl GROUP BY **userid**;

### ✅ MySQL의 내장함수 중 집계(그룹) 함수

- sum()
- min()
- max()
- count()
- avg()

### ✅ HAVING

- 집계함수는 기본적으로 WHERE 절과 사용 불가, 이때 HAVING 절 사용
- HAVING 절은 GROUP BY절 다음에 나와야 함
- SELECT userid, SUM(price*amount) FROM buytbl GROUP BY userid HAVING SUM(price*amount) > 1000;

### 데이터 수정: Update

UPDATE 테이블이름
SET 열1='값1', 열2='값2', ...
WHERE 조건;

```sql
UPDATE testtbl4 SET lname = '없음' WHERE fname = 'Kyoichi';
```

### 데이터의 삭제: DELETE FROM

DELETE FROM 테이블이름
WHERE 조건;

```sql
DELETE FROM testtbl4 WHERE fname='Aamer';
```

### ✅ 트랜잭션 작업 시 오류가 발생하여 처리한 작업들을 취소

트랜잭션: 작업의 단위 

insert

insert

이전 insert 취소 → rollback: 커밋 이전 모든 수정 작업 취소

insert

update

update

DB에 반영 → commit: 수정작업을 DB 반영, 커밋한 내용은 롤백 안됨

Auto commit

- commit
- transaction
- rollback
- savepoint

### ✅ 조인(Join)

조인이란 두 개 이상의 테이블을 서로 묶어서 하나의 결과 집합으로 만들어 냄

**Inner Join**
양쪽 모두 값이 있는 경우만 합침

SELECT <열 목록>
FROM <첫 번째 테이블>
INNER JOIN <두 번째 테이블>
ON <조인될 조건>
[WHERE 검색조건]

```sql
SELECT DISTINCT U.serID, [U.name](http://u.name/), U.addr
FROM usertbl U
INNER JOIN buytbl B
ON U.userID = B.userID
ORDER BY U.userID;
```

**Outer Join**
조건에 만족되지 않는 행도 포함

SELECT <열 목록>
FROM <테이블1(LEFT 테이블)>
<LEFT|RIGHT|FULL> OUTER JOIN <테이블2(RIGHT 테이블)>  -- 왼쪽|오른쪽|모든 테이블 전부 나와라
ON <조인될 조건>
[WHERE 검색조건]

### ✅ SELECT문이 실행되는 순서

FROM, JOIN → WHERE → GROUP BY → HAVING → SELECT → ORDER BY →LIMIT

---

# JDBC란?

데이터베이스에 저장된 데이터를 Java에서 사용할 수 있도록 하는 자바 API

### ✅ JDBC가 제공하는 기능

```java
Jdbc로 SELECT실행하는 코드
sql = "SELECT…"
ResultSet rs = stmt.executeQuery(sql)
```

```java
JDBC로 INSERT 실행하는 코드
sql = "INSERT / UPDATE / DELETE…"
stmt.executeUpdate(sql)
```

---

# 스프링 프레임워크란?

### ✅ 주요 특징

- 경량 컨테이너 - 객체 생성, 소멸과 같은 생명 주기를 관리하며 스프링 컨테이너로부터 필요한 객체를 얻어올 수 있음
- (IoC) 제어의 역행 지원 (필요한 객체를 만드는 것이 아니라 공급받는 것) - 메서드나 객체의 호출 제어권이 사용자가 아니라 프레임워크에 있어서 필요에 따라 스프링에서 사용자의 코드를 호출함 (함수가 호출 당하는 것)
- (DI) 의존성 주입 지원 - 각각의 계층이나 서비스 간에 의존성이 존재할 경우 프레임워크가 서로 연결해줌

```sql
@Controller
public class YaksokController {

	@Autowired
	private YaksokRepository ysRepo;     // IoC, DI 
	
	@GetMapping("/yaksok/count")
	@ResponseBody
	public String count() {              // IoC
		return ""+ysRepo.count();
	}
	
	@RequestMapping("/yaksok/list")
	@ResponseBody
	public List<Yaksok> list() {
		return ysRepo.findAll();
	}
}

```

- (AOP) 관점 지향 프로그래밍 지원 - 트랜잭션이나 로킹, 보안과 같이 여러 모듈에서 공통적으로 사용하는 기능의 경우 해당 기능을 분리하여 관리할 수 있음

### Spring MVC 패턴이란?

> 동작원리
> 

![Untitled](https://github.com/user-attachments/assets/78aa24de-3855-4731-a4cd-aca3b4702ff2)

- Controller 요청 → Repository → DBMS → View(결과)

![Untitled](https://github.com/user-attachments/assets/bb11a746-6654-49b7-90f8-3effbc88b2b6)


> MVC 패턴의 조건
> 
1. Model은 Controller와 View에 의존하지 않아야 한다. (Model 내부에 Controller와 View에 관련된 코드가 있으면 안된다)
2. View는 Model에만 의존해야 하고, Controller에는 의존하면 안된다. (View 내부에 Model 코드만 있을 수 있고, Controller의 코드가 있으면 안된다)
3. View가 Model로부터 데이터를 받을 때는, 사용자마다 다르게 보여줘야 하는 데이터에 대해서만 받아야 한다.
4. Controller는 Model과 View에 의존해도 된다.
5. View가 Model로부터 데이터를 받을 때, 반드시 Controller에서 받아야 한다.

### 요청 url 연결하는 방법

**@Controller 가 적용된 클래스**

**@RequestMapping**("/hello")를 이용하여 요청 URL 및 요청 메소드 파악 

- 메소드 단위로 요청을 처리
- 메소드 결과를 출력할 뷰 이름 반환

**@GetMapping**("/hello")

**@PostMapping**("/hello")

**@RequestParam**

"no" 요청 파라미터를 추출하기 위한 서비스 메서드의 선언부

```sql
public String hello( @RequestParam("no") int no )
```
