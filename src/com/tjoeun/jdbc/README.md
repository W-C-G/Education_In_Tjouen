# JDBC
### DBMS, Oracle, MySQL, SQL...
### Java-MySQL 연동 => JDBC 규격준수 라이브러리 필요

### DAO(Data Access Object)
Connection, Statemnet, Result를 사용하여 SQL 쿼리를 사용하기 위한 환경을 조성
별개의 함수를 만들어 적용할 때,   
1) conn으로 DB에 접속
2) 쿼리문 작성
3) 실행   
Tip) getConn() 함수를 작성해서 다른 메서드를 제작할 때, 실행시켜주자 
```
Connection conn = DriverManager.getConnection(url, root, password); //url로 추적하여 드라이브 매니저로 연결
Statement stmt = conn.createStatement(); //연결된 객체로 실행할 문장 형태 만들기
ResultSet rs = stmt.executeQuery(sql); // 문장 형태에 sql 삽입 후 실행

while(rs.next()){
  var something = rs.getInt/getString/....
}
```



### VO(Value Object
가장 기본적인 객체. 일반적으로 만드는 클래스


### Statement
* 일반 Statement
* PreparedStatemnet
Statament에 비해 컴파일을 1번만 실행하기 때문에 성능이 더 유리하다. 또한 객체를 캐시에 담아 재사용하기 때문에 반복적인 쿼리 사용 시 성능이 좋다. 실행할 SQL문에는 '%d', '%s' 대신 '?'.
```
PreparedStatement pstmt = conn.prepareSTatement(sql);
pstmt.setInt(1, 20); // 1번째 위치(?가 있는 위치)에 20 대입
pstmt.setString(2, "Scott"); //2번째 위치에 "Scott" 대입
```

---
### Description File
##### EmpDAO: Emp table에 대한 Data Access Object
##### EmpVO: Emp Value Object
##### MemberDAO: Member Data Access Object
##### MemberVO: Member Value Object
##### MySQL_CRUD: MemberVO, MemberDAO를 활용하여 MySQL과 연결해서 CRUD를 실행.
