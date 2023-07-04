package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleUtility {
	
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@//localhost:1521/XE";
		String id = "iclass";
		String pw = "0419";
		
		try {
			conn = DriverManager.getConnection(url,id,pw);
			
		
			
		}catch(SQLException e) {
			System.out.println("오류 : " + e);
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		
		try {
			if(conn != null) {
				conn.close();
				System.out.println("Connection closed!");
			}else {
				System.out.println("Connection is null! you can not close it!");
			}
		}catch(SQLException e) {
			System.out.println("오류 : " + e );
		}
	}
}
