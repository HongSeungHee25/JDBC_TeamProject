package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	    	  list.add(new Payment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
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
	    	  pm = new Payment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7));
	      }
	      return pm;
	}
	
}
