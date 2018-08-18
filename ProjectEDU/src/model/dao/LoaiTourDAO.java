package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.dao.khoi.ConnectDB;

public class LoaiTourDAO {

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaLoai,TenLoai from LoaiTour";
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
	/*public boolean addListTinh(List<DLTinh> listTinh) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		
		String sqlInsert = "insert into Tinh(MaTinh,TenTinh) values (?,?)";
		String sqlUpdate = "update Tinh set TenTinh=? where MaTinh=?";
		String sqlCheck = "select MaTinh from Tinh where MaTinh=?";
        PreparedStatement stmtc = null;
        PreparedStatement stmte = null;
        try {
        	for(DLTinh ttp : listTinh){
        		stmtc = con.getConnect().prepareStatement(sqlCheck);
        		stmtc.setString(1, ttp.getMaTinh());
        		if(stmtc.executeQuery().next()){
        			stmte = con.getConnect().prepareStatement(sqlUpdate);
        			stmte.setString(1, ttp.getTenTinh());
        			stmte.setString(2, ttp.getMaTinh());
        		} else {
        			stmte = con.getConnect().prepareStatement(sqlInsert);
        			stmte.setString(1, ttp.getMaTinh());
        			stmte.setString(2, ttp.getTenTinh());
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
	}*/
}
