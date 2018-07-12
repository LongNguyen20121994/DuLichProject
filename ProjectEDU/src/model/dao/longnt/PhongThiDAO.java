package model.dao.longnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.PhongThi;
import model.dao.khoi.ConnectDB;

public class PhongThiDAO {
	public PhongThi getInfo(String maPT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaPT,MaDDT,TenPT from PhongThi where MaPT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maPT);
    		ResultSet rs = stmt.executeQuery();
    		PhongThi pt = null;
    		if(rs.next()){
    			pt = new PhongThi();
    			pt.setMaPT(rs.getString(1));
    			pt.setMaDDT(rs.getString(2));
    			pt.setTenPT(rs.getString(3));
    		} 
        	stmt.close();
        	return pt;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}

	public boolean insertPhongThi(PhongThi pt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaPT from PhongThi where MaPT=?";
		String sql = "insert into PhongThi(MaPT,MaDDT,TenPT) values(?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, pt.getMaPT());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, pt.getMaPT());
    			stmt.setString(2, pt.getMaDDT());
    			stmt.setString(3, pt.getTenPT());
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
		String sql = "select MaPT,TenPT from PhongThi";
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

	public List<PhongThi> getAll() {
		List<PhongThi> list = new ArrayList<PhongThi>();
		ConnectDB con = new ConnectDB();
		String sql = "select MaPT,MaDDT,TenPT from PhongThi";
		con.openConnection();
		try {
			PreparedStatement stmt = con.getConnect().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			PhongThi pt;
			while(rs.next()){
				pt = new PhongThi();
				pt.setMaPT(rs.getString(1));
				pt.setMaDDT(rs.getString(2));
				pt.setTenPT(rs.getString(3));
				list.add(pt);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeConnection();
		}
		return list;
	}

	public boolean updatePhongThi(PhongThi pt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update PhongThi set MaDDT=?,TenPT=? where MaPT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, pt.getMaDDT());
			stmt.setString(2, pt.getTenPT());
			stmt.setString(3, pt.getMaPT());
			if(stmt.executeUpdate() > 0){
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

	public boolean deleteListPhongThi(List<String> listMaPT) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaPT.size() - 1; i++) {
			str += listMaPT.get(i) + "','";
		}
		str += listMaPT.get(listMaPT.size() - 1) + "')";
		String sql = "delete from PhongThi where MaPT in " + str;
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
}