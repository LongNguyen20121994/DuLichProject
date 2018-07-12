package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import model.bean.XaPhuong;

public class XaPhuongDAO {

	public HashMap<String, String> getListXaSelect(String maTinh, String maHuyen) {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaXa,TenXa from Xa_Phuong where MaTinh=? and MaHuyen=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTinh);
    		stmt.setString(2, maHuyen);
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

	public boolean addListXaPhuong(List<XaPhuong> listXa) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		
		String sqlInsert = "insert into Xa_Phuong(MaXa,MaHuyen,MaTinh,TenXa) values (?,?,?,?)";
		String sqlUpdate = "update Xa_Phuong set TenXa=? where MaXa=? and MaHuyen=? and MaTinh=?";
		String sqlCheck = "select * from Xa_Phuong where MaXa=? and MaHuyen=? and MaTinh=?";
        PreparedStatement stmtc = null;
        PreparedStatement stmte = null;
        try {
        	for(XaPhuong xp : listXa){
        		stmtc = con.getConnect().prepareStatement(sqlCheck);
        		stmtc.setString(1, xp.getMaXa());
        		stmtc.setString(2, xp.getMaHuyen());
        		stmtc.setString(3, xp.getMaTinh());
        		if(stmtc.executeQuery().next()){
        			stmte = con.getConnect().prepareStatement(sqlUpdate);
        			stmte.setString(1, xp.getTenXa());
        			stmte.setString(2, xp.getMaXa());
        			stmte.setString(3, xp.getMaHuyen());
        			stmte.setString(4, xp.getMaTinh());
        		} else {
        			stmte = con.getConnect().prepareStatement(sqlInsert);
        			stmte.setString(1, xp.getMaXa());
        			stmte.setString(2, xp.getMaHuyen());
        			stmte.setString(3, xp.getMaTinh());
        			stmte.setString(4, xp.getTenXa());
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
