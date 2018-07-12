package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.NamTuyenSinh;

public class NamTuyenSinhDAO {

	public int getNamTuyenSinh() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select top 1 NamTS from NamTuyenSinh order by NamTS desc";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			return rs.getInt(1);
    		} 
        	stmt.close();
        	return 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            con.closeConnection();
        }
	}

	public boolean updateNamTuyenSinh(NamTuyenSinh namTS) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlInsert = "insert into NamTuyenSinh(NamTS,GhiChu) values(?,?)";
		String sqlUpdate = "update NamTuyenSinh set GhiChu=? where NamTS=?";
		String sqlCheck = "select NamTS from NamTuyenSinh where NamTS=?";
        PreparedStatement stmt = null;
        PreparedStatement stmtExec = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setInt(1, namTS.getNamTS());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(rs.next()){
    			stmtExec = con.getConnect().prepareStatement(sqlUpdate);
    			stmtExec.setString(1, namTS.getGhiChu());
    			stmtExec.setInt(2, namTS.getNamTS());
    			check = stmtExec.executeUpdate();
    		} else {
    			stmtExec = con.getConnect().prepareStatement(sqlInsert);
    			stmtExec.setInt(1, namTS.getNamTS());
    			stmtExec.setString(2, namTS.getGhiChu());
    			check = stmtExec.executeUpdate();
    		}
    		stmtExec.close();
        	stmt.close();
			if (check > 0) {
				return true;
			}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.closeConnection();
        }
        return false;
	}

}
