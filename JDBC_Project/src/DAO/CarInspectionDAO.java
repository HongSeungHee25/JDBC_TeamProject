package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Car_Inspection;
import DTO.Car_rent;


@SuppressWarnings("unused")
public class CarInspectionDAO {
	
	private static CarInspectionDAO carDao = new CarInspectionDAO();
	private CarInspectionDAO() {}
	public static CarInspectionDAO getCarInspectionDAO() {
		return carDao;
	}
	
	//자동차 검사 조회 DAO - 병인
		public List<Car_Inspection> selectAll() throws SQLException{
		      Connection conn = OracleUtility.getConnection();
		      String select = "select * from car_inspection order by Next_date";
		      PreparedStatement ps = conn.prepareStatement(select);
		      ResultSet rs = ps.executeQuery();
		      List<Car_Inspection> list = new ArrayList<>();
		      while(rs.next()) {
		         
		         list.add(new Car_Inspection(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4)));
		      }
		      
		      return list;
		   }
		 
		//자동차 번호로 검사 조회 DAO - 병인 
		public Car_Inspection selectByCarNo(String car_no) throws SQLException{
		   Connection conn = OracleUtility.getConnection();
		   String select = "select * from car_inspection where car_no = ?";
		   PreparedStatement ps = conn.prepareStatement(select);
		   ps.setString(1, car_no);
		   ResultSet rs = ps.executeQuery();
		   Car_Inspection ci = null;
		   if(rs.next()) {
		      
		      ci = new Car_Inspection(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4));
		   }
		   return ci;
		}
		
		

}
