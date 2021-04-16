package com.tjoeun.jdbc;

import java.sql.*;

/*
 * 1. Connection(Interface): DrvierManager.getConnection(url, root, password)
 * 	  DriverManager가 객체를 생성하여 리턴해준다. (url에는 원하는 DB를 선택해놓고 있다.)
 *    
 * 2. Statement(Interface): Connection.createstatement()으로 Connection이 Statement 객체를 생성. 
 *      SQL문을 실행할 수 있는 executeQuery 메서드가 있음
 * 		ex) Statement.excuteQuery(SQL Query)
 * 		The object used for executing a static SQL statementand returning the results it produces
 * 
 * 3. ResultSet: SQL쿼리를 실행해서 나온 결과를 set 형태로 저장
 * 
 *    ex) ResultSet.next() = iterator. next() 응용
 *        반복문으로 ResultSet.getString(칼럼명) -> String
 * 
 * 
 * * DAO: Database Access Object, 데이터베이스에 접근하는 객체(예) insert()를 통해 DB에 입력 or 업데이트
 * * VO: Value Object(NonLogic, 예) get() or set() 함수, Member 객체
 * 
 */
public class MySQL_Test {

   public static void main(String[] args) 
   {
   // JDBC Interface
   Connection conn = null;
   Statement stmt = null;
   try{
      System.out.println("Connecting to database...");
      
      // 접속할 DB 주소
      String url = "jdbc:mysql://localhost:3306/world?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
      
      conn = DriverManager.getConnection(url, "root", "dncjf1357"); // url, root_id, password
      
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      
      String sql = "SELECT * FROM country WHERE code='KOR'";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         //Retrieve by column name
         String name  = rs.getString("name");
         String continent = rs.getString("continent");
         String capital = rs.getString("capital");

         //Display values
         System.out.print(name);
         System.out.print("\t" + continent);
         System.out.print("\t" + capital);
      }

      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }//end try
   System.out.println("\nGoodbye!");
}
}//end main
}//end class


