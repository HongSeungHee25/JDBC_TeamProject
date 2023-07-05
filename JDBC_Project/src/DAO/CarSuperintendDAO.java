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
	      return list;
	}
	
	//자동차 번호로 조회 DAO - 병인
	public Car_Superintend selectByCarNo(String car_no) throws SQLException{
		   Connection conn = OracleUtility.getConnection();
		   String select = "select * from Car where car_no = ?";
		   PreparedStatement ps = conn.prepareStatement(select);
		   ps.setString(1, car_no );
		   ResultSet rs = ps.executeQuery();
		   Car_Superintend cs = null;
		   if(rs.next()) {
		      
		      cs = new Car_Superintend(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
		   }
		   return cs;
		}
	
}
