package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.DLPhuongTien;
import model.dao.khoi.ConnectDB;

public class PhuongTienDAO {

	public boolean insertPhuongTien(DLPhuongTien mt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaPT from PhuongTien where MaPT=?";
		String sql = "insert into PhuongTien(MaPT,TenPT,MaTinh,MoTa) values (?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, mt.getMaPT());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, mt.getMaPT());
    			stmt.setString(2, mt.getTenPT());
    			stmt.setString(3, mt.getMaTinh());
    			stmt.setString(4, mt.getMoTa());
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
		String sql = "select MaPT,TenPT from PhuongTien";
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

	public List<DLPhuongTien> getAll() {
		List<DLPhuongTien> list = new ArrayList<DLPhuongTien>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaPT,TenPT,MaTinh,MoTa from PhuongTien";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLPhuongTien mt;
    		while(rs.next()){
    			mt = new DLPhuongTien();
    			mt.setMaPT(rs.getString(1));
    			mt.setTenPT(rs.getString(2));
    			mt.setMaTinh(rs.getString(3));
    			mt.setMoTa(rs.getString(4));
    			list.add(mt);
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

	public boolean deleteListPhuongTien(List<String> listMaMT) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaMT.size() - 1; i++) {
			str += listMaMT.get(i) + "','";
		}
		str += listMaMT.get(listMaMT.size() - 1) + "')";
		String sql = "delete from PhuongTien where MaPT in " + str;
		PreparedStatement stmt = null;
		connectDB.openConnection();
		try {
			stmt = connectDB.getConnect().prepareStatement(sql);
			if (stmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.closeConnection();
		}
		return false;
	}

	public boolean updatePhuongTien(DLPhuongTien mt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update PhuongTien set TenPT=?, MaTinh=?, MoTa=? where MaPT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, mt.getTenPT());
    		stmt.setString(2, mt.getMaTinh());
    		stmt.setString(3, mt.getMoTa());
    		stmt.setString(4, mt.getMaPT());
    		if(stmt.executeUpdate() > 0) {
    			return true;
    		}
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.closeConnection();
        }
		return false;
	}

	public String getMaxRecord() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaPT from PhuongTien ORDER BY MaPT DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLPhuongTien tur = null;
    		if(rs.next()){
    			tur = new DLPhuongTien();
    			tur.setMaPT(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaPT();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
}
