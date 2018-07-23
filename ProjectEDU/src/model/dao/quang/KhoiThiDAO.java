package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.bean.KhoiThi;
import model.dao.khoi.ConnectDB;

public class KhoiThiDAO {

	public boolean insertKhoiThi(KhoiThi kt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaKhoi from KhoiThi where MaKhoi=?";
		String sql = "insert into KhoiThi(MaKhoi,TenKhoi) values (?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, kt.getMaKhoi());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, kt.getMaKhoi());
    			stmt.setString(2, kt.getTenKhoi());
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

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKhoi,TenKhoi from KhoiThi";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			list.put(rs.getString(1), rs.getString(2));
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
