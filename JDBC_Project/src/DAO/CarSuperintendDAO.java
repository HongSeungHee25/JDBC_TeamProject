package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Car_Superintend;



public class CarSuperintendDAO {
	
	private static CarSuperintendDAO superintendDAO = new CarSuperintendDAO();
	private CarSuperintendDAO() {}
	public static CarSuperintendDAO getCarSuperintendDAO() {
		return superintendDAO;
	}
	
	//자동차 정보 조회 DAO - 병인
		public List<Car_Superintend> selectAll() throws SQLException{
		      Connection conn = OracleUtility.getConnection();
		      String select = "select * from car";
		      PreparedStatement ps = conn.prepareStatement(select);
		      ResultSet rs = ps.executeQuery();
		      List<Car_Superintend> list = new ArrayList<>();
		      while(rs.next()) {
		    	  list.add(new Car_Superintend(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
		      }
		      conn.close();
		      ps.close();
		      rs.close();
		      return list;
		}
		
		//자동차 정보 DAO - 승희
		public List<String[]> getCarNamesByGrade(String carGrade) throws SQLException {
		    Connection conn = OracleUtility.getConnection();
		    String sql = "SELECT car_type, price, insurance, pl, car_no FROM car WHERE car_grade = ?";
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, carGrade); // 자동차 등급을 매개변수로 설정
		    ResultSet rs = ps.executeQuery();

		    List<String[]> carNameByGrade = new ArrayList<>();
		    while (rs.next()) {
		        String[] gradeNames = new String[5]; // 배열 크기를 5로 수정
		        for (int i = 0; i < 5; i++) {
		            gradeNames[i] = rs.getString(i + 1); // 컬럼 인덱스는 1부터 시작하므로 i+1 사용
		        }
		        carNameByGrade.add(gradeNames);
		    }
		    conn.close();
		    ps.close();
		    rs.close();

		    return carNameByGrade;
		}


		
		//자동차 등급 DAO - 승희
		public List<String> car_grade() throws SQLException {
		    Connection connection = OracleUtility.getConnection();
		    String sql = "SELECT car_grade\r\n"
		    		+ "FROM CAR\r\n"
		    		+ "GROUP BY car_grade\r\n"
		    		+ "ORDER BY CASE car_grade\r\n"
		    		+ "    WHEN '경차' THEN 1\r\n"
		    		+ "    WHEN '소형차' THEN 2\r\n"
		    		+ "    WHEN '중형차' THEN 3\r\n"
		    		+ "    WHEN '대형차' THEN 4\r\n"
		    		+ "    ELSE 5\r\n"
		    		+ "END";
		    PreparedStatement ps = connection.prepareStatement(sql);
		    List<String> carGrades = new ArrayList<>();

		    ResultSet rs = ps.executeQuery();
		    while (rs.next()) {
		        String carGrade = rs.getString("car_grade");
		        carGrades.add(carGrade);
		    }

		    connection.close();
		    ps.close();
		    rs.close();

		    return carGrades;
		}

		
	
}
