package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.bean.DanToc;

public class DanTocDAO {

	public boolean addListDanToc(List<DanToc> listDanToc) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		
		String sqlInsert = "insert into DanToc(MaDT,TenDanToc,GhiChu) values (?,?,?)";
		String sqlUpdate = "update DanToc set TenDanToc=?,GhiChu=? where MaDT=?";
		String sqlCheck = "select MaDT from DanToc where MaDT=?";
        PreparedStatement stmtc = null;
        PreparedStatement stmte = null;
        try {
        	for(DanToc dt : listDanToc){
        		stmtc = con.getConnect().prepareStatement(sqlCheck);
        		stmtc.setString(1, dt.getMaDT());
        		if(stmtc.executeQuery().next()){
        			stmte = con.getConnect().prepareStatement(sqlUpdate);
        			stmte.setString(1, dt.getTenDT());
        			stmte.setString(2, dt.getGhiChu());
        			stmte.setString(3, dt.getMaDT());
        		} else {
        			stmte = con.getConnect().prepareStatement(sqlInsert);
        			stmte.setString(1, dt.getMaDT());
        			stmte.setString(2, dt.getTenDT());
        			stmte.setString(3, dt.getGhiChu());
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
