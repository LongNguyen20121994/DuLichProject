package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.KhoiThi;

public class KhoiThiDAO {

	public boolean insertKhoiThi(KhoiThi kt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaKhoi from KhoiThi where MaKhoi=?";
		String sql = "insert into KhoiThi(MaKhoi,TenKhoi) values (?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, kt.getMaKhoi());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, kt.getMaKhoi());
    			stmt.setString(2, kt.getTenKhoi());
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
		String sql = "select MaKhoi,TenKhoi from KhoiThi";
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

	public List<KhoiThi> getAll() {
		List<KhoiThi> list = new ArrayList<KhoiThi>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKhoi,TenKhoi from KhoiThi";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		KhoiThi kt;
    		while(rs.next()){
    			kt = new KhoiThi();
    			kt.setMaKhoi(rs.getString(1));
    			kt.setTenKhoi(rs.getString(2));
    			list.add(kt);
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

	public boolean deleteListKhoiThi(List<String> listMaKT) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaKT.size() - 1; i++) {
			str += listMaKT.get(i) + "','";
		}
		str += listMaKT.get(listMaKT.size() - 1) + "')";
		String sql = "delete from KhoiThi where MaKhoi in " + str;
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

	public boolean updateKhoiThi(KhoiThi kt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update KhoiThi set TenKhoi=? where MaKhoi=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, kt.getTenKhoi());
    		stmt.setString(2, kt.getMaKhoi());
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

}
