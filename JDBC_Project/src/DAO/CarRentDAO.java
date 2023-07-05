package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Car_rent;




public class CarRentDAO {
	
	private static CarRentDAO rentDAO = new CarRentDAO();
	private CarRentDAO() {}
	public static CarRentDAO getCarRentDAO() {
		return rentDAO;
	}
	
	//자동차 예약 조회 DAO - 병인
	public List<Car_rent> selectAll() throws SQLException{
	      Connection conn = OracleUtility.getConnection();
	      String select = "select * from car_rent";
	      PreparedStatement ps = conn.prepareStatement(select);
	      ResultSet rs = ps.executeQuery();
	      List<Car_rent> list = new ArrayList<>();
	      while(rs.next()) {
	    	  list.add(new Car_rent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
	      }
	      conn.close();
		   ps.close();
		   rs.close();
	      
	      return list;
	}
	
	//자동차 렌트 예약 번호로 조회 DAO - 병인
	public Car_rent selectByRentNo(int rent_no) throws SQLException{
		   Connection conn = OracleUtility.getConnection();
		   String select = "select * from car_rent where rent_no = ?";
		   PreparedStatement ps = conn.prepareStatement(select);
		   ps.setInt(1, rent_no );
		   ResultSet rs = ps.executeQuery();
		   Car_rent cr = null;
		   if(rs.next()) {
		      
		      cr = new Car_rent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		   }
		   conn.close();
		   ps.close();
		   rs.close();
		   
		   return cr;
		}
	public int insertReserve(Car_rent data) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String select = "insert into car_rent "
				+ "values(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(select);
		Car_rent cr = null;
		ps.setInt(1, cr.getRent_no());
		ps.setString(2, cr.getName());
		ps.setString(3, cr.getCar_no());
		ps.setString(4, cr.getRent_start());
		ps.setString(5, cr.getRent_end());
		
		
		int result = ps.executeUpdate();
		
		conn.close();
		ps.close();
		return result;
	}
	
}
