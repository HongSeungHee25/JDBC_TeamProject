package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Customer;
import DTO.Reservation;
import oracle.sql.ORAData;

public class CustomerDAO {
	
	private static CustomerDAO dao = new CustomerDAO();
	private CustomerDAO() {}
	public static CustomerDAO getCustomerDAO() {
		return dao;
	}

	//회원) 로그인 DAO - 승희
	public Customer login(String id, String password) throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select name from customer where customer_id = ? and pw = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		Customer result = null;
		
		ps.setString(1, id);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			result = Customer.builder()
					.name(rs.getString(1))
					.build();
		}
		connection.close();
		ps.close();
		rs.close();
		
		return result;
	}
	
	//회원) 회원가입 DAO - 승희
		public boolean join(Customer dto)throws SQLException{
			Connection connection = OracleUtility.getConnection();
			String sql = "insert into customer values(?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getCustomer_id());
			ps.setString(3, dto.getPw());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getLicence());
			
			int result = ps.executeUpdate();
			
			connection.close();
			ps.close();
			return result > 0;
		}
		//관리자) 회원 조회 DAO - 병인
				public List<Customer> selectAll() throws SQLException{
				      Connection conn = OracleUtility.getConnection();
				      String select = "select * from customer";
				      PreparedStatement ps = conn.prepareStatement(select);
				      ResultSet rs = ps.executeQuery();
				      List<Customer> list = new ArrayList<>();
				      while(rs.next()) {
				    	  list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
				      }
				      return list;
				}
				
				//관리자) 회원 이름으로 조회 DAO - 병인
				public Customer selectByName(String name) throws SQLException{
				      Connection conn = OracleUtility.getConnection();
				      String select = "select * from Customer where name = ?";
				      PreparedStatement ps = conn.prepareStatement(select);
				      ps.setString(1, name);
				      ResultSet rs = ps.executeQuery();
				      Customer cs = null;
				      if(rs.next()) {
				    	  cs = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				      }
				      return cs;
				}
				public int customerUpdate(Customer customer) throws SQLException{
					Connection connection = OracleUtility.getConnection();
					String sql = "UPDATE customer SET customer_id = ?, pw = ?, phone = ? WHERE name = ?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, customer.getCustomer_id());
					ps.setString(2, customer.getPw());
					ps.setString(3, customer.getPhone());
					ps.setString(4, customer.getName());
					
					int result = ps.executeUpdate();
					
					return result;
				}
				
				//회원 로그인해서 예약 내역 조회하기 DAO
				public List<Reservation> getReservationsByCustomer(String name) throws SQLException {
				    List<Reservation> reservations = new ArrayList<>();
				    
				    Connection connection = OracleUtility.getConnection();
				    String sql = "SELECT " +
				            "c.CAR_NO, " +
				            "c.CAR_TYPE, " +
				            "TO_CHAR(cr.RENT_START, 'yyyy-mm-dd') AS RENT_START, " +
				            "TO_CHAR(cr.RENT_END, 'yyyy-mm-dd') AS RENT_END, " +
				            "p.PAYMENT_METHOD, " +
				            "c.PRICE + c.INSURANCE AS money " +
				            "FROM " +
				            "CAR c " +
				            "JOIN CAR_RENT cr ON c.CAR_NO = cr.CAR_NO " +
				            "JOIN PAYMENT p ON c.CAR_NO = p.CAR_NO " +
				            "WHERE " +
				            "cr.NAME = ? " +
				            "ORDER BY RENT_START";
				    
				    PreparedStatement ps = connection.prepareStatement(sql);
				    ps.setString(1, name);
				    
				    ResultSet rs = ps.executeQuery();
				    
				    while (rs.next()) {
				        String carNo = rs.getString("CAR_NO");
				        String carType = rs.getString("CAR_TYPE");
				        String rentStart = rs.getString("RENT_START");
				        String rentEnd = rs.getString("RENT_END");
				        String paymentMethod = rs.getString("PAYMENT_METHOD");
				        int money = rs.getInt("money");
				        
				        Reservation reservation = new Reservation(carNo, carType, rentStart, rentEnd, paymentMethod, money);
				        reservations.add(reservation);
				    }
				    
				    rs.close();
				    ps.close();
				    connection.close();
				    
				    return reservations;
				}


				
				
				
				
				
}
