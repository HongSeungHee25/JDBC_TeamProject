package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Customer;
import DTO.CustomerServiceCenter;
import DTO.Reservation;

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
				    String sql = "SELECT\r\n"
				    		+ "    b.car_no,\r\n"
				    		+ "    a.car_type,\r\n"
				    		+ "    TO_CHAR(b.rent_start, 'yyyy-mm-dd') AS rent_start,\r\n"
				    		+ "    TO_CHAR(b.rent_end, 'yyyy-mm-dd') AS rent_end,\r\n"
				    		+ "    (a.PRICE + a.INSURANCE) * (b.rent_end - b.rent_start) AS MONEY\r\n"
				    		+ "FROM\r\n"
				    		+ "    CAR a\r\n"
				    		+ "JOIN\r\n"
				    		+ "    CAR_RENT b ON a.car_no = b.car_no\r\n"
				    		+ "WHERE\r\n"
				    		+ "    b.NAME = ?\r\n"
				    		+ "ORDER BY\r\n"
				    		+ "    b.rent_start";
				    
				    PreparedStatement ps = connection.prepareStatement(sql);
				    ps.setString(1, name);
				    
				    ResultSet rs = ps.executeQuery();
				    
				    while (rs.next()) {
				        String carNo = rs.getString("CAR_NO");
				        String carType = rs.getString("CAR_TYPE");
				        String rentStart = rs.getString("RENT_START");
				        String rentEnd = rs.getString("RENT_END");
				        int money = rs.getInt("money");
				        
				        Reservation reservation = new Reservation(carNo, carType, rentStart, rentEnd,  money);
				        reservations.add(reservation);
				    }
				    
				    rs.close();
				    ps.close();
				    connection.close();
				    
				    return reservations;
				}
				
				//회원) 고객센터 - 승희
				public boolean service(CustomerServiceCenter dto)throws SQLException{
					Connection connection = OracleUtility.getConnection();
					String sql = "insert into CustomerServiceCenter values(service_seq.nextval,?,?,?,?)";
					
					PreparedStatement ps = connection.prepareStatement(sql);
					
					ps.setString(1, dto.getName());
					ps.setString(2, dto.getCustomer_id());
					ps.setString(3, dto.getPhone());
					ps.setString(4, dto.getService());
					
					int result = ps.executeUpdate();
					
					connection.close();
					ps.close();
					return result > 0;
				}
				
				//고객센터 접수 내역 답변 DAO - 승희
				public CustomerServiceCenter service(String name) throws SQLException{
				      Connection conn = OracleUtility.getConnection();
				      String select = "SELECT SERVICE_NO,SERVICE FROM CUSTOMERSERVICECENTER c WHERE name = ?";
				      PreparedStatement ps = conn.prepareStatement(select);
				      ps.setString(1, name);
				      ResultSet rs = ps.executeQuery();
				      CustomerServiceCenter cs = null;
				      if(rs.next()) {
				    	  cs = new CustomerServiceCenter(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				      }
				      return cs;
				}
				
				
				
}
