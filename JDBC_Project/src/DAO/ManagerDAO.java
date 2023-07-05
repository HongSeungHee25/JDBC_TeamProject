package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Car_Superintend;
import DTO.Month_total;
import DTO.Payment;

public class ManagerDAO {
	
	private static ManagerDAO dao = new ManagerDAO();
	private ManagerDAO() {}
	public static ManagerDAO getManagerDAO() {
		return dao;
	}

	//회원별 매출 DAO - 승희
	public List<Payment> sales() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		List<Payment> saleslist = new ArrayList<>();
		
		String sql = "SELECT * FROM total_price";
		try(
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				) {
			while(rs.next()) {
				int payment_id = rs.getInt(1);
				String name = rs.getString(2);
				String payment_day = rs.getString(3);
				int money = rs.getInt(4);
				String payment_method = rs.getString(5);
				String car_no = rs.getString(6);
				
				Payment pay = Payment.builder()
						.payment_id(payment_id)
						.name(name)
						.payment_day(payment_day)
						.money(money)
						.payment_method(payment_method)
						.car_no(car_no)
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
	
	//월별 토탈 DAO - 승희
	public List<Month_total> month_total() throws SQLException{
		Connection connection = OracleUtility.getConnection();
		
		String sql = "SELECT * FROM month_total";
		
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
	
	
	
	
}
