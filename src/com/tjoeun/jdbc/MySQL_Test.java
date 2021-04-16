package com.tjoeun.jdbc;

import java.sql.*;

/*
 * 1. Connection(Interface): DrvierManager.getConnection(url, root, password)
 * 	  DriverManager�� ��ü�� �����Ͽ� �������ش�. (url���� ���ϴ� DB�� �����س��� �ִ�.)
 *    
 * 2. Statement(Interface): Connection.createstatement()���� Connection�� Statement ��ü�� ����. 
 *      SQL���� ������ �� �ִ� executeQuery �޼��尡 ����
 * 		ex) Statement.excuteQuery(SQL Query)
 * 		The object used for executing a static SQL statementand returning the results it produces
 * 
 * 3. ResultSet: SQL������ �����ؼ� ���� ����� set ���·� ����
 * 
 *    ex) ResultSet.next() = iterator. next() ����
 *        �ݺ������� ResultSet.getString(Į����) -> String
 * 
 * 
 * * DAO: Database Access Object, �����ͺ��̽��� �����ϴ� ��ü(��) insert()�� ���� DB�� �Է� or ������Ʈ
 * * VO: Value Object(NonLogic, ��) get() or set() �Լ�, Member ��ü
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
      
      // ������ DB �ּ�
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


