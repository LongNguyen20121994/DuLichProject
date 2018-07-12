package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import model.bean.TinhThanhPho;
import model.dao.khoi.ConnectDB;

public class TinhThanhPhoDAO {

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list;
        PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTinh,TenTinh from Tinh_ThanhPho";

        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		list = new HashMap<String, String>();
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

	public boolean addListTinhTP(List<TinhThanhPho> listTinh) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		
		String sqlInsert = "insert into Tinh_ThanhPho(MaTinh,TenTinh) values (?,?)";
		String sqlUpdate = "update Tinh_ThanhPho set TenTinh=? where MaTinh=?";
		String sqlCheck = "select MaTinh from Tinh_ThanhPho where MaTinh=?";
        PreparedStatement stmtc = null;
        PreparedStatement stmte = null;
        try {
        	for(TinhThanhPho ttp : listTinh){
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
	}
}
