package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Customer;

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
	public void join(Customer dto)throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "insert into customer values(?,?,?,?,?)";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, dto.getCustomer_id());
		ps.setString(2, dto.getPw());
		ps.setString(3, dto.getName());
		ps.setString(4, dto.getPhone());
		ps.setString(5, dto.getLicence());
		
		ps.execute();
		
		connection.close();
		ps.close();
	}
		//관리자) 회원 조회 DAO - 병인
		public List<Customer> selectAll() throws SQLException{
		      Connection conn = OracleUtility.getConnection();
		      String select = "select * from car_rent";
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
	
}
