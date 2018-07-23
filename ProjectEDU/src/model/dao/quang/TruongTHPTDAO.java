package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.dao.khoi.ConnectDB;

public class TruongTHPTDAO {

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list;
        PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTruong,TenTruong from TruongTHPT";

        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		list = new HashMap<String, String>();
    		
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

	public HashMap<String, String> getListTHPTSelect(String maTinh) {
		HashMap<String, String> list;
        PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		
		con.openConnection();
		String sql = "select MaTruong,TenTruong from TruongTHPT where MaTinh=?";
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTinh);
    		ResultSet rs = stmt.executeQuery();
    		
    		list = new HashMap<String, String>();
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
