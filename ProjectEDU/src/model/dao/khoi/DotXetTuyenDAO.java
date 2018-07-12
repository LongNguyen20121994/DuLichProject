package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.DotXetTuyen;

public class DotXetTuyenDAO {

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaDotXT,TenDotXT from DotXetTuyen where TrangThai='true'";
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

	public List<DotXetTuyen> getListDotXT() {
		List<DotXetTuyen> list = new ArrayList<DotXetTuyen>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaDotXT,TenDotXT,TrangThai from DotXetTuyen";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			list.add(new DotXetTuyen(rs.getString(1), rs.getString(2) + (rs.getBoolean(3)?" - (Mở)":" - (Đóng)"), rs.getBoolean(3)));
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

	public boolean insertDotXetTuyen(DotXetTuyen dxt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into DotXetTuyen(MaDotXT,TenDotXT) values(?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, dxt.getMaDotXT());
			stmt.setString(2, dxt.getTenDotXT());
			if (stmt.executeUpdate() > 0) {
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

	public boolean updateTrangThai(String maDotXT, boolean b) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update DotXetTuyen set TrangThai='false'";
		String sqlUpdate = "update DotXetTuyen set TrangThai=? where MaDotXT=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.executeUpdate();
			stmt.close();
			stmt = con.getConnect().prepareStatement(sqlUpdate);
			stmt.setBoolean(1, b);
			stmt.setString(2, maDotXT);
			if (stmt.executeUpdate() > 0) {
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

	public boolean deleteDotXetTuyen(String maDotXT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "delete from DotXetTuyen where MaDotXT=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, maDotXT);
			if (stmt.executeUpdate() > 0) {
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
