package model.dao.longnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.bean.CumThi;
import model.dao.khoi.ConnectDB;

public class CumThiDAO {
	
	public boolean insertCumThi(CumThi ct) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaCT from CumThi where MaCT=?";
		String sql = "insert into CumThi(MaCT,TenCumThi) values(?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, ct.getMaCT());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, ct.getMaCT());
    			stmt.setString(2, ct.getTenCumThi());
    			check = stmt.executeUpdate();
    		} 
        	stmt.close();
        	if(check > 0){
        		return true;
        	}
        	return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
	}
	
	public CumThi getInfo(String maCT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaCT,TenCumThi from CumThi where MaCT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maCT);
    		ResultSet rs = stmt.executeQuery();
    		CumThi ct = null;
    		if(rs.next()){
    			ct = new CumThi();
    			ct.setMaCT(rs.getString(1));
    			ct.setTenCumThi(rs.getString(2));
    		} 
        	stmt.close();
        	return ct;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaCT,TenCumThi from CumThi";
        PreparedStatement stmt = null;
        try {
        	stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			list.put(rs.getString(1), rs.getString(1) + " - " + rs.getString(2));
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
		return list;
	}
	
	public HashMap<String, String> getSelectCumThi(String soCMND) {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaCT,TenCumThi from CumThi as ct inner join QuanLyCumThi as ql on ct.MaCT = ql.DonViThi where ql.SoCMND = ?";
        PreparedStatement stmt = null;
        try {
        	stmt = con.getConnect().prepareStatement(sql);
        	stmt.setString(1, soCMND);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			list.put(rs.getString(1), rs.getString(1) + " - " + rs.getString(2));
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
		return list;
	}

	public HashMap<String, String> getListCumThiSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaCT,TenCumThi from CumThi";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			list.put(rs.getString(1), rs.getString(1) + " - " + rs.getString(2));
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
		return list;
	}
}
