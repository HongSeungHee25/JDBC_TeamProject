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
		      return list;
		}
		
		public Car_rent selectByName(String name) throws SQLException{
			   Connection conn = OracleUtility.getConnection();
			   String select = "SELECT RENT_NO , name, CAR_NO ,TO_CHAR(RENT_START,'yyyy-mm-dd') AS rent_start,TO_CHAR(RENT_END,'yyyy-mm-dd') AS RENT_END  FROM CAR_RENT cr WHERE name = ? AND rownum = 1 ORDER BY RENT_NO DESC";
			   PreparedStatement ps = conn.prepareStatement(select);
			   ps.setString(1, name);
			   ResultSet rs = ps.executeQuery();
			   Car_rent cr = null;
			   if(rs.next()) {
			      
			      cr = new Car_rent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			   }
			   
			   return cr;
			}
	
		// rent_no 가져오기 위한 가장 최근 데이터 저장값 불러오기 - 병인
				public Car_rent selectOne() throws SQLException{
					   Connection conn = OracleUtility.getConnection();
					   String select = "SELECT * FROM CAR_RENT cr \r\n"
					   		+ "WHERE rownum = 1\r\n"
					   		+ "ORDER BY RENT_NO desc";
					   PreparedStatement ps = conn.prepareStatement(select);
					   ResultSet rs = ps.executeQuery();
					   Car_rent cr = null;
					   if(rs.next()) {
					      
					      cr = new Car_rent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
					   }
					   
					   return cr;
					}
				
				// 예약한 정보 insert - 병인
				public int insertReserve(Car_rent data) throws SQLException{
					Connection conn = OracleUtility.getConnection();
					String select = "insert into car_rent "
							+ "values(rent_no_seq.nextval,?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(select);
					
					ps.setString(1, data.getName());
					ps.setString(2, data.getCar_no());
					ps.setString(3, data.getRent_start());
					ps.setString(4, data.getRent_end());
					
					
					int result = ps.executeUpdate();
					
					conn.close();
					ps.close();
					return result;
				}
				//중복 예약 불가능 DAO - 승희
				public boolean isReservationAvailable(String car_no) throws SQLException {
				    Connection connection = OracleUtility.getConnection();

				    String sql = "SELECT CASE " +
				                 "           WHEN cr.RENT_START > SYSDATE THEN 0 " +
				                 "           WHEN cr.RENT_START < SYSDATE AND cr.RENT_END > SYSDATE THEN 0 " +
				                 "           ELSE 1 " +
				                 "         END AS IS_AVAILABLE " +
				                 "FROM CAR c " +
				                 "LEFT JOIN CAR_RENT cr ON c.CAR_NO = cr.CAR_NO " +
				                 "WHERE c.CAR_NO = ?";
				    PreparedStatement ps = connection.prepareStatement(sql);
				    ps.setString(1, car_no);
				    ResultSet rs = ps.executeQuery();

				    boolean isAvailable = false;
				    if (rs.next()) {
				        int availability = rs.getInt("IS_AVAILABLE");
				        isAvailable = (availability == 1);
				    }

				    connection.close();
				    ps.close();
				    rs.close();

				    return isAvailable;
				}

		
			
}
