package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.bean.TruongTHPT;

public class TruongTHPTDAO {

	public boolean addListTruongTHPT(List<TruongTHPT> list) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		
		String sqlInsert = "insert into TruongTHPT(MaTinh,MaTruong,TenTruong,KhuVucUT,DiaChi) values (?,?,?,?,?)";
		String sqlUpdate = "update TruongTHPT set TenTruong=?,KhuVucUT=?,DiaChi=? where MaTinh=? and MaTruong=?";
		String sqlCheck = "select MaTruong from TruongTHPT where MaTinh=? and MaTruong=?";
        PreparedStatement stmtc = null;
        PreparedStatement stmte = null;
        try {
        	for(TruongTHPT tr : list){
        		stmtc = con.getConnect().prepareStatement(sqlCheck);
        		stmtc.setString(1, tr.getMaTinh());
        		stmtc.setString(2, tr.getMaTruong());
        		if("".equals(tr.getKhuVucUT())){
    				tr.setKhuVucUT(null);
    			}
        		if(stmtc.executeQuery().next()){
        			stmte = con.getConnect().prepareStatement(sqlUpdate);
        			stmte.setString(1, tr.getTenTruong());
        			stmte.setString(2, tr.getKhuVucUT());
        			stmte.setString(3, tr.getDiaChi());
        			stmte.setString(4, tr.getMaTinh());
        			stmte.setString(5, tr.getMaTruong());
        		} else {
        			stmte = con.getConnect().prepareStatement(sqlInsert);
        			stmte.setString(1, tr.getMaTinh());
        			stmte.setString(2, tr.getMaTruong());
        			stmte.setString(3, tr.getTenTruong());
        			stmte.setString(4, tr.getKhuVucUT());
        			stmte.setString(5, tr.getDiaChi());
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

	public boolean insertTruongTHPT(TruongTHPT truong) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaTruong from TruongTHPT where MaTinh=? and MaTruong=?";
		String sqlInsert = "insert into TruongTHPT(MaTinh,MaTruong,TenTruong,KhuVucUT,DiaChi) values (?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
        	stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, truong.getMaTinh());
    		stmt.setString(2, truong.getMaTruong());
    		if("".equals(truong.getKhuVucUT())){
				truong.setKhuVucUT(null);
			}
    		int check = 0;
    		if(!stmt.executeQuery().next()) {
    			stmt = con.getConnect().prepareStatement(sqlInsert);
    			stmt.setString(1, truong.getMaTinh());
    			stmt.setString(2, truong.getMaTruong());
    			stmt.setString(3, truong.getTenTruong());
    			stmt.setString(4, truong.getKhuVucUT());
    			stmt.setString(5, truong.getDiaChi());
    		}
    		stmt.executeUpdate();
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

}
