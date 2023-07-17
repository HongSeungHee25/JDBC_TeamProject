package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Car_Inspection;
import DTO.Car_Superintend;
import DTO.CustomerServiceCenter;
import DTO.CustomerServiceCenter_Maneger;
import DTO.Manager_Pay;
import DTO.Month_total;
import DTO.Payment;

public class ManagerDAO {
	
	private static ManagerDAO dao = new ManagerDAO();
	private ManagerDAO() {}
	public static ManagerDAO getManagerDAO() {
		return dao;
	}

	//회원별 매출 DAO - 승희 (수정 중)
		public List<Manager_Pay> sales() throws SQLException {
			Connection connection = OracleUtility.getConnection();
			List<Manager_Pay> saleslist = new ArrayList<>();
			
			String sql = "SELECT name, total_money, payment_method,\r\n"
					+ "       CASE\r\n"
					+ "           WHEN total_money >= 500000 THEN 'VIP'\r\n"
					+ "           WHEN total_money >= 300000 THEN 'GOLD'\r\n"
					+ "           WHEN total_money >= 200000 THEN 'SILVER'\r\n"
					+ "           ELSE 'FAMILY'\r\n"
					+ "       END AS grade\r\n"
					+ "FROM (\r\n"
					+ "    SELECT p.name, SUM(money) AS total_money, p.payment_method\r\n"
					+ "    FROM PAYMENT p\r\n"
					+ "    JOIN CAR c ON p.car_no = c.car_no\r\n"
					+ "    GROUP BY p.name, p.payment_method\r\n"
					+ ") subquery\r\n"
					+ "ORDER BY name";
			try(
					PreparedStatement ps = connection.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					) {
				while(rs.next()) {
					String name = rs.getString(1);
					int money = rs.getInt(2);
					String payment_method = rs.getString(3);
					String grade = rs.getString(4);
					
					Manager_Pay pay = Manager_Pay.builder()
							.name(name)
							.money(money)
							.payment_method(payment_method)
							.grade(grade)
							.build();
					
					saleslist.add(pay);
				}
				
			} catch (SQLException e) {
				 e.printStackTrace();
			}
			return saleslist;
		}
	
	//예약 상태 조회 DAO - 승희 
	public List<Car_Superintend> status() throws SQLException{
		Connection connection = OracleUtility.getConnection();
		
		String sql = "SELECT c.CAR_NO, c.CAR_GRADE, c.CAR_TYPE,\r\n"
				+ "  (CASE\r\n"
				+ "    WHEN cr.RENT_START > SYSDATE THEN '예약 중'\r\n"
				+ "    WHEN cr.RENT_START < SYSDATE AND cr.RENT_END > SYSDATE THEN '대여 중'\r\n"
				+ "    ELSE '대여 가능'\r\n"
				+ "  END) AS RENT_TYPE, c.PRICE , c.INSURANCE ,c.PL\r\n"
				+ "FROM CAR c\r\n"
				+ "LEFT JOIN CAR_RENT cr ON c.CAR_NO = cr.CAR_NO";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<Car_Superintend> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new Car_Superintend(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
		}
		return list;
		
	}
	
	//월별 토탈 DAO - 승희 (수정)
	public List<Month_total> month_total() throws SQLException{
		Connection connection = OracleUtility.getConnection();
		
		String sql = "SELECT \r\n"
				+ "to_char(payment_day,'yyyy-mm') AS months, payment_method, money AS total\r\n"
				+ "FROM ( SELECT p.payment_id, p.name, p.payment_day, p.money, p.payment_method, p.car_no\r\n"
				+ "FROM PAYMENT p \r\n"
				+ "JOIN CAR c\r\n"
				+ "ON p.car_no = c.car_no)\r\n"
				+ "GROUP BY to_char(payment_day,'yyyy-mm'), payment_method, money\r\n"
				+ "ORDER BY months DESC";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<Month_total> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new Month_total(rs.getString(1), rs.getString(2),rs.getInt(3)));
		}
		return list;
	}
	
	//월별 토탈 신용카드 or 계좌이체 나눠서 조회 DAO - 승희
	public Month_total payment_method(String pay)throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "SELECT * FROM MONTH_TOTAL mt WHERE payment_method = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, pay);
		ResultSet rs = ps.executeQuery();
		Month_total total = null;
				if(rs.next()) {
					total = new Month_total(rs.getString(1), rs.getString(2), rs.getInt(3));
				}
				return total;
	}
	
	//차량 등록
	public boolean car_insert(Car_Superintend dto)throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql ="insert into car values(?,?,?,null,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, dto.getCar_no());
		ps.setString(2, dto.getCar_garde());
		ps.setString(3, dto.getCarType());
		ps.setInt(4, dto.getPrice());
		ps.setInt(5, dto.getInsurance());
		ps.setString(6, dto.getPL());
		
		int result = ps.executeUpdate();
		
		connection.close();
		ps.close();
		return result > 0;
	}
	// 자동차 검사 등록 DAO - 승희
	public boolean insertInspection(Car_Inspection car) throws SQLException {
	    Connection conn = OracleUtility.getConnection();
	    String select = "INSERT INTO car_inspection VALUES (?, ?, ?, ?)";
	    PreparedStatement ps = conn.prepareStatement(select);
	    
	    ps.setString(1, car.getCar_no());
	    ps.setString(2, car.getInspection_type());
	    ps.setDate(3, car.getLast_date());
	    ps.setDate(4, car.getNext_date());
	    
	    int result = ps.executeUpdate();
	    
	    conn.close();
	    ps.close();
	    
	    return result > 0;
	}
	
		//고객센터 내용 DAO - 승희
			public List<CustomerServiceCenter_Maneger> selectAll() throws SQLException{
			      Connection conn = OracleUtility.getConnection();
			      String select = "SELECT * FROM CUSTOMERSERVICECENTER";
			      PreparedStatement ps = conn.prepareStatement(select);
			      ResultSet rs = ps.executeQuery();
			      List<CustomerServiceCenter_Maneger> list = new ArrayList<>();
			      while(rs.next()) {
			    	  list.add(new CustomerServiceCenter_Maneger(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			      }
			      return list;
			}
	
	
}
