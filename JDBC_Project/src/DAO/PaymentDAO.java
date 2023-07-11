package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DTO.Car_rent;
import DTO.Payment;





public class PaymentDAO {
	
	private static PaymentDAO dao = new PaymentDAO();
	private PaymentDAO() {}
	public static PaymentDAO getPaymentDAO() {
		return dao;
	}

	//결제정보 전체 조회 DAO - 병인
		public List<Payment> selectAll() throws SQLException{
		      Connection conn = OracleUtility.getConnection();
		      String select = "select * from Payment";
		      PreparedStatement ps = conn.prepareStatement(select);
		      ResultSet rs = ps.executeQuery();
		      List<Payment> list = new ArrayList<>();
		      while(rs.next()) {
		    	  list.add(new Payment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
		      }
		      return list;
		}
	
		//결제정보 이름으로 조회 DAO - 병인
		public Payment selectByName(String name) throws SQLException{
		      Connection conn = OracleUtility.getConnection();
		      String select = "select * from Payment where name = ?";
		      PreparedStatement ps = conn.prepareStatement(select);
		      ps.setString(1, name);
		      ResultSet rs = ps.executeQuery();
		      Payment pm = null;
		      if(rs.next()) {
		    	  pm = new Payment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getInt(5), rs.getString(6), rs.getString(7));
		      }
		      return pm;
		}
		
		// 결제정보 데이터 insert DAO - 병인
				public int insertPayment(Payment data) throws SQLException{
					Connection conn = OracleUtility.getConnection();
					String select = "insert into payment "
							+ "values(payment_id_seq.nextval,?,?,sysdate,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(select);
					
					ps.setString(1, data.getName());
					ps.setInt(2, data.getRent_no());
					ps.setInt(3, data.getMoney());
					ps.setString(4, data.getPayment_method());
					ps.setString(5, data.getCar_no());
					
					
					int result = ps.executeUpdate();
					
					conn.close();
					ps.close();
					return result;
				}
		
		    
		    
		public int getLatestRentNo() throws SQLException {
		    Connection connection = OracleUtility.getConnection();
		    String query = "SELECT MAX(rent_no) AS max_rent_no FROM car_rent";
		    PreparedStatement preparedStatement = connection.prepareStatement(query);
		    ResultSet resultSet = preparedStatement.executeQuery();

		    int rentNo = 0;

		        if (resultSet.next()) {
		            rentNo = resultSet.getInt("max_rent_no");
		        }

		    return rentNo;
		}

	
}
