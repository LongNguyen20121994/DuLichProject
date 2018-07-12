package model.dao.longnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.bean.MonThi;
import model.dao.khoi.ConnectDB;

public class MonThiDAO {

	public boolean insertMonThi(MonThi mt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaMon from MonThi where MaMon=?";
		String sql = "insert into MonThi(MaMon,TenMonThi) values (?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, mt.getMaMonThi());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, mt.getMaMonThi());
    			stmt.setString(2, mt.getTenMonThi());
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
		String sql = "select MaMon,TenMonThi from MonThi";
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
