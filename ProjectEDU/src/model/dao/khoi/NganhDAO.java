package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import model.bean.Nganh;

public class NganhDAO {

	public boolean addListNganh(List<Nganh> list) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		
		String sqlInsert = "insert into Nganh(MaNganh,TenNganh) values (?,?)";
		String sqlUpdate = "update Nganh set TenNganh=? where MaNganh=?";
		String sqlCheck = "select MaNganh from Nganh where MaNganh=?";
        PreparedStatement stmtc = null;
        PreparedStatement stmte = null;
        try {
        	for(Nganh tr : list){
        		stmtc = con.getConnect().prepareStatement(sqlCheck);
        		stmtc.setString(1, tr.getMaNganh());
        		if(stmtc.executeQuery().next()){
        			stmte = con.getConnect().prepareStatement(sqlUpdate);
        			stmte.setString(1, tr.getTenNganh());
        			stmte.setString(2, tr.getMaNganh());
        		} else {
        			stmte = con.getConnect().prepareStatement(sqlInsert);
        			stmte.setString(1, tr.getMaNganh());
        			stmte.setString(2, tr.getTenNganh());
        		}
        		stmte.executeUpdate();
        	}
        	stmtc.close();
        	stmte.close();
        	return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
	}

	public boolean insertNganh(Nganh nganh) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaNganh from Nganh where MaNganh=?";
		String sql = "insert into Nganh(MaNganh,TenNganh) values (?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, nganh.getMaNganh());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, nganh.getMaNganh());
    			stmt.setString(2, nganh.getTenNganh());
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
		String sql = "select MaNganh,TenNganh from Nganh";
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
