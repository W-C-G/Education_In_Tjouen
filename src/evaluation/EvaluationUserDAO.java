package evaluation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluationUserDAO {
	// DB Connection Part
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	
	// DB Connection Method
	public List<User> getDBTable() {
		System.out.println("Connecting to database...");
		
		List<User> tmp_list = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(url, "root", "dncjf1357"); // url, root_id, password
		
			String sql = "SELECT * FROM user";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String upass = rs.getString("upass");
				
				tmp_list.add(new User(uid, username, upass));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return tmp_list;
	}
	
	public void closeAll() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
